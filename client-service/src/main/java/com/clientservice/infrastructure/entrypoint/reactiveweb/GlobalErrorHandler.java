package com.clientservice.infrastructure.entrypoint.reactiveweb;

import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.infrastructure.entrypoint.reactiveweb.exceptions.TechnicalExceptions;
import com.clientservice.infrastructure.entrypoint.reactiveweb.exceptions.TechnicalExceptionsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class GlobalErrorHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = resolveHttpStatus(ex);
        Map<String, Object> error = buildErrorBody(ex);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        byte[] bytes = serialize(error);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));

    }

    private Map<String, Object> buildErrorBody(Throwable ex) {
        if (ex instanceof BusinessException businessEx) {
            return toMap(
                    businessEx.getBusinessMessageException().getCode(),
                    "BUSINESS",
                    businessEx.getMessage()
            );
        } else if (ex instanceof TechnicalExceptions technicalEx) {
            return toMap(
                    technicalEx.getTechnicalExceptionsMessage().getCode(),
                    ex.getMessage().contains("user") ? "EXTERNAL ERROR" : "TECHNICAL",
                    technicalEx.getMessage()
            );
        } else {
            return toMap(
                    TechnicalExceptionsMessage.UNKNOWN.getCode(),
                    "UNKNOWN",
                    TechnicalExceptionsMessage.UNKNOWN.getMessage()
            );
        }
    }
    private Map<String, Object> toMap(String code, String type, String message) {
        return Map.of(
                "code", code,
                "type", type,
                "message", message,
                "date", LocalDateTime.now().toString()
        );
    }

    private byte[] serialize(Map<String, Object> errorBody) {
        try {
            return objectMapper.writeValueAsString(errorBody).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            return ("{\"code\":\"SERIALIZATION_ERROR\",\"type\":\"TECHNICAL\",\"message\":\"Error serializing response\",\"date\":\"" + LocalDateTime.now() + "\"}")
                    .getBytes(StandardCharsets.UTF_8);
        }
    }
    private HttpStatus resolveHttpStatus(Throwable ex){
        if (ex instanceof BusinessException businessException){
            return switch (businessException.getBusinessMessageException()){
                case CLIENT_EXIST -> HttpStatus.CONFLICT;
                case CLIENT_INVALID -> HttpStatus.BAD_REQUEST;
                case CLIENT_NOT_EXIST -> HttpStatus.NOT_FOUND;
                default -> HttpStatus.INTERNAL_SERVER_ERROR;
            };
        }else if (ex instanceof TechnicalExceptions technicalExceptions){
            return switch (technicalExceptions.getTechnicalExceptionsMessage()){
                case EXTERNAL_ERROR -> TechnicalExceptionsMessage.EXTERNAL_ERROR.getStatus();
                case EXTERNAL_ERROR_USER -> TechnicalExceptionsMessage.EXTERNAL_ERROR_USER.getStatus();
                default -> TechnicalExceptionsMessage.INTERNAL_SERVER_ERROR.getStatus();
            };
        }else {
            return TechnicalExceptionsMessage.UNKNOWN.getStatus();
        }
    }
}
