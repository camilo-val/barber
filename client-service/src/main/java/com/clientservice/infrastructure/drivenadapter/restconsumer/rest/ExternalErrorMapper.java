package com.clientservice.infrastructure.drivenadapter.restconsumer.rest;

import com.clientservice.infrastructure.entrypoint.reactiveweb.exceptions.TechnicalExceptions;
import com.clientservice.infrastructure.entrypoint.reactiveweb.exceptions.TechnicalExceptionsMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class ExternalErrorMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Function<ClientResponse, Mono<? extends  Throwable>> handleError(String source){
        return clientResponse -> clientResponse.bodyToMono(String.class)
                .flatMap(body -> {
                    try {
                        HttpStatusCode status = clientResponse.statusCode();
                        JsonNode json = objectMapper.readTree(body);
                        String code = json.has("code")? json.get("code").asText() : "UNKNOWN";
                        if ((status == HttpStatus.NOT_FOUND || status == HttpStatus.CONFLICT) && code.equals("SFB001") || code.equals("SFB002")){
                            return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.EXTERNAL_ERROR_USER));
                        }
                        if (status == HttpStatus.BAD_REQUEST  || code.equals("SFB000")){
                            return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.EXTERNAL_ERROR_USER));
                        }
                        if (status == HttpStatus.INTERNAL_SERVER_ERROR && code.equals("999")){
                            return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.EXTERNAL_ERROR_USER));
                        }
                        return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.UNKNOWN));
                    }catch (Exception e){
                        return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.UNKNOWN));
                    }
                });

    }
}
