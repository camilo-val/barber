package com.clientservice.infrastructure.entrypoint.reactiveweb;

import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.domain.model.exceptions.message.BusinessMessageException;
import com.clientservice.domain.usercase.*;
import com.clientservice.infrastructure.entrypoint.reactiveweb.dto.ClientRequestDto;
import com.clientservice.infrastructure.entrypoint.reactiveweb.mapper.ClientMapper;
import com.clientservice.infrastructure.entrypoint.reactiveweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class Handler {
    private final ClientUseCase clientUseCase;
    private final FindClientUseCase findClient;

    public Mono<ServerResponse> findById(ServerRequest request){
        return this.findClient.findById(request.pathVariable("id"))
                .flatMap(ServerResponse.ok()::bodyValue)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessMessageException.BAD_REQUEST)));

    }

    public  Mono<ServerResponse> createClient(ServerRequest request){
        return request.bodyToMono(ClientRequestDto.class)
                .flatMap(client -> this.clientUseCase.create(ClientMapper.toClient(client), UserMapper.toUser(client.getUser())))
                            .flatMap(response -> ServerResponse.created(request.uri()).bodyValue(response))
                .switchIfEmpty(Mono.error(new BusinessException(BusinessMessageException.BAD_REQUEST)));
    }

}
