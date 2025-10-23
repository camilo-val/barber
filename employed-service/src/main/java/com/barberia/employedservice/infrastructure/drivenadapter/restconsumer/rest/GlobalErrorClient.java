package com.barberia.employedservice.infrastructure.drivenadapter.restconsumer.rest;

import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.exceptions.TechnicalExceptions;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.exceptions.TechnicalExceptionsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class GlobalErrorClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public Function<ClientResponse, Mono<? extends Throwable>> handle(){
        return clientResponse -> clientResponse.bodyToMono(String.class)
                .flatMap(body ->{
                            try {
                                HttpStatusCode httpStatus = clientResponse.statusCode();
                                JsonNode jsonNode =objectMapper.readTree(body);
                                String code = jsonNode.has("code") ? jsonNode.get("code").asText() : "UNKNOWN";
                                if ((httpStatus == HttpStatus.NOT_FOUND || httpStatus == HttpStatus.CONFLICT)
                                        && (code.equals("SFB001") || code.equals("SFB002"))){
                                    return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.EXTERNAL_ERROR_USER));
                                }else if (httpStatus == HttpStatus.BAD_REQUEST && code.equals("SFB000")){
                                    return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.EXTERNAL_ERROR));
                                }else {
                                    return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.EXTERNAL_UNKNOWN));
                                }
                            } catch (JsonProcessingException e) {
                                return Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.UNKNOWN));
                            }
                        }
                );
    }

}
