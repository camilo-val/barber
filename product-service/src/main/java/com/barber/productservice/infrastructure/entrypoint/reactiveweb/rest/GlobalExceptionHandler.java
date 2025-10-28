package com.barber.productservice.infrastructure.entrypoint.reactiveweb.rest;

import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception.TechnicalExceptions;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception.TechnicalExceptionsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = resolveStatusCode(ex);
        Map<String, Object> error = buildMap(ex);
        byte [] toJson = serialize(error);
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.defer(() ->
                Mono.just(response.bufferFactory().wrap(toJson))));    }

    private HttpStatus resolveStatusCode(Throwable throwable){
        if (throwable instanceof BusinessExceptions businessMessageExceptions){
            return switch (businessMessageExceptions.getBusinessMessageExceptions()){
                case PRODUCT_EXIST, INVALID_PRODUCT -> HttpStatus.CONFLICT;
                case PRODUCT_NOT_EXIST -> HttpStatus.NOT_FOUND;
                default -> HttpStatus.INTERNAL_SERVER_ERROR;
            };
        } else if (throwable instanceof TechnicalExceptions technicalExceptions) {
            return switch (technicalExceptions.getTechnicalExceptionsMessage()){
                case BAD_REQUEST -> HttpStatus.BAD_REQUEST;
                case TIME_OUT -> HttpStatus.REQUEST_TIMEOUT;
                case INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
                default -> TechnicalExceptionsMessage.INTERNAL_SERVER_ERROR.getStatus();
            };
        }else{
            return TechnicalExceptionsMessage.UNKNOWN.getStatus();
        }
    }

    private Map<String, Object> buildMap(Throwable throwable){
        if (throwable instanceof  BusinessExceptions businessExceptions){
            return toMap(businessExceptions.getBusinessMessageExceptions().getCode(),
                    businessExceptions.getMessage(),
                    "Business");
        }
        else if (throwable instanceof TechnicalExceptions technicalExceptions){
            return toMap(technicalExceptions.getTechnicalExceptionsMessage().getCode(),
                    technicalExceptions.getMessage(),
                    "Technical");
        }else{
           return toMap(TechnicalExceptionsMessage.INTERNAL_SERVER_ERROR.getCode(), throwable.getMessage(), "Unknown");
        }
    }

    private Map<String, Object> toMap(String code, String message, String type){
        return Map.of("code", code,
                "message", message,
                "type", type,
                "date",LocalDateTime.now().toString());
    }

    private byte[] serialize(Map<String, Object> errorBody) {
        System.out.println("MAPPER ERROR: " + errorBody);
        try {
            return objectMapper.writeValueAsString(errorBody).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            return ("{\"code\":\"SERIALIZATION_ERROR\",\"type\":\"TECHNICAL\",\"message\":\"Error serializing response\",\"date\":\"" + LocalDateTime.now() + "\"}")
                    .getBytes(StandardCharsets.UTF_8);
        }
    }
}
