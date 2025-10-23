package com.barberia.userservice.infrastrcuture.entrypoint.rest;

import com.barberia.userservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.userservice.domain.model.exceptions.message.BusinessMessageException;
import com.barberia.userservice.infrastrcuture.entrypoint.exceptions.TechnicalExceptions;
import com.barberia.userservice.infrastrcuture.entrypoint.exceptions.TechnicalExceptionsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(-2)
public class GlobalHandlerError implements ErrorWebExceptionHandler {
    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus httpStatus = null;
        Map<String,Object> error = new HashMap<>();
        if (ex instanceof BusinessExceptions businessExceptions){
            if (ex.getMessage().equals(BusinessMessageException.USER_EXIST.getMessage())){
                httpStatus = HttpStatus.CONFLICT;
            }else if(ex.getMessage().equals(BusinessMessageException.USER_NOT_EXIST.getMessage())){
                httpStatus = HttpStatus.NOT_FOUND;
            }else {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
            error = toMap(businessExceptions.getBusinessMessageException().getCode(),"BUSINESS",ex.getMessage());
        }else if(ex instanceof TechnicalExceptions technicalExceptions){
            error = toMap(technicalExceptions.getTechnicalExceptionsMessage().getCode(),"TECHNICAL",ex.getMessage());
        }else {
            error = toMap(TechnicalExceptionsMessage.UNKNOWN.getCode(), "UNKNOWN", TechnicalExceptionsMessage.UNKNOWN.getMessage());
        }
        String json = new ObjectMapper().writeValueAsString(error);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }

    private Map<String, Object> toMap(String code, String type, String message){
       return Map.of("code", code, "type", type, "message",message,"date", LocalDateTime.now().toString());
    }
}
