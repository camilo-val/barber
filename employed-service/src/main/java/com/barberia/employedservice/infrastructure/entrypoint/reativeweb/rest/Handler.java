package com.barberia.employedservice.infrastructure.entrypoint.reativeweb.rest;

import com.barberia.employedservice.domain.usecase.EmployedUseCase;
import com.barberia.employedservice.domain.usecase.FindEmployedUseCase;
import com.barberia.employedservice.domain.usecase.UserUseCase;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.dto.EmployedRequestDto;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.exceptions.TechnicalExceptions;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.exceptions.TechnicalExceptionsMessage;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.mapper.EmployedMapper;
import com.barberia.employedservice.infrastructure.entrypoint.reativeweb.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class Handler {
    private final EmployedUseCase employedUseCase;
    private final FindEmployedUseCase findEmployedUseCase;
    private final UserUseCase userUseCase;

    Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(EmployedRequestDto.class)
                .zipWhen(employed -> userUseCase.save(UserMapper.toUser(employed.getUser())))
                .flatMap(tuple -> this.employedUseCase.createEmployed(EmployedMapper.toEmployed(tuple.getT1()),tuple.getT2())
                        .map(newEmployed -> EmployedMapper.toResponse(newEmployed,tuple.getT1().getUser())))
                .flatMap(response -> ServerResponse.created(request.uri()).bodyValue(response))
                .switchIfEmpty(Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.BAD_REQUEST)));
    }
}
