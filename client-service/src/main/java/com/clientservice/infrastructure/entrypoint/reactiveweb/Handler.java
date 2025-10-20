package com.clientservice.infrastructure.entrypoint.reactiveweb;

import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.domain.model.exceptions.message.BusinessMessageException;
import com.clientservice.domain.usercase.*;
import com.clientservice.infrastructure.entrypoint.reactiveweb.dto.ClientRequestDto;
import com.clientservice.infrastructure.entrypoint.reactiveweb.mapper.ClientMapper;
import com.clientservice.infrastructure.entrypoint.reactiveweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class Handler {
    private final ClientUseCase clientUseCase;
    private final FindClient findClient;

    public Mono<ServerResponse> findById(ServerRequest request){
        return this.findClient.findById(request.pathVariable("id"))
                .flatMap(ServerResponse.ok()::bodyValue)
                .onErrorResume(BusinessException.class, ex ->
                        ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(Map.of("message", ex.getMessage()))
                .switchIfEmpty(Mono.error(new BusinessException(BusinessMessageException.BAD_REQUEST))));

    }

    public  Mono<ServerResponse> createClient(ServerRequest request){
        return request.bodyToMono(ClientRequestDto.class)
                .flatMap(client -> this.clientUseCase.create(ClientMapper.toClient(client), UserMapper.toUser(client.getUser())))
                            .flatMap(response -> ServerResponse.created(request.uri()).bodyValue(response))
                .onErrorResume(BusinessException.class, ex->
                        ServerResponse.status(HttpStatus.CONFLICT).bodyValue(Map.of("message",ex.getMessage())))
                .switchIfEmpty(Mono.error(new BusinessException(BusinessMessageException.BAD_REQUEST)));
    }

}
