package com.barberia.employedservice.infrastructure.entrypoint.reativeweb.rest;

import com.barberia.employedservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.employedservice.domain.model.exceptions.BusinessExceptionsMessages;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.exceptions.TechnicalExceptions;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.exceptions.TechnicalExceptionsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class GlobalErrorHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status= resolveHttpStatus(ex);


        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(status);
        byte[] serialize = serialize(buildToErrorResponse(ex));
        return response.writeWith(Mono.just(response.bufferFactory().wrap(serialize)));
    }

    private HttpStatus resolveHttpStatus(Throwable ex){
        if (ex instanceof BusinessExceptions businessExceptions){
            return switch (businessExceptions.getBusinessExceptionsMessages()){
                case CLIENT_EXIST -> HttpStatus.CONFLICT;
                case CLIENT_NOT_EXIST -> HttpStatus.NOT_FOUND;
                case INVALID_CLIENT -> HttpStatus.BAD_GATEWAY;
                default -> HttpStatus.INTERNAL_SERVER_ERROR;
            };
        }else if(ex instanceof TechnicalExceptions technicalExceptions){
            return switch (technicalExceptions.getTechnicalExceptionsMessage()){
                case BAD_REQUEST -> HttpStatus.BAD_REQUEST;
                case EXTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
                case INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
                case EXTERNAL_UNKNOWN -> HttpStatus.BAD_GATEWAY;
                case EXTERNAL_ERROR_USER -> HttpStatus.CONFLICT;
                default -> TechnicalExceptionsMessage.INTERNAL_SERVER_ERROR.getHttpStatus();
            };
        }
        return TechnicalExceptionsMessage.UNKNOWN.getHttpStatus();
    }
    private Map<String,Object> buildToErrorResponse(Throwable ex){
        if (ex instanceof BusinessExceptions businessExceptions){
            String code = businessExceptions.getBusinessExceptionsMessages().getCode();
            log.error("code: {}, message: {}, type: {}" ,code, ex.getMessage(),"BUSINESS");
            return toMap(code,
                    "BUSINESS",
                    ex.getMessage());
        }
        if(ex instanceof TechnicalExceptions technicalExceptions){
            String code = technicalExceptions.getTechnicalExceptionsMessage().getCode();
            log.error("code: {}, message: {}, type: {}" ,code, ex.getMessage(),"BUSINESS");
            return toMap(technicalExceptions.getTechnicalExceptionsMessage().getCode(),
                    "TECHNICAL",
                    ex.getMessage());
        }
        return toMap(TechnicalExceptionsMessage.UNKNOWN.getCode(),
                "UNKNOWN",
                    TechnicalExceptionsMessage.UNKNOWN.getMessage());
    }

    private Map<String,Object> toMap(String code, String type, String message){
        return Map.of("code", code,
                "type",type,
                "message",message);
    }
    private byte[] serialize(Map<String, Object> errorBody) {
        try {
            return objectMapper.writeValueAsString(errorBody).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            return ("{\"code\":\"SERIALIZATION_ERROR\",\"type\":\"TECHNICAL\",\"message\":\"Error serializing response\",\"date\":\"" + LocalDateTime.now() + "\"}")
                    .getBytes(StandardCharsets.UTF_8);
        }
    }
}
