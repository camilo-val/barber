package com.barberia.userservice.infrastrcuture.entrypoint.rest;

import com.barberia.userservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.userservice.domain.usecase.DeleteUserUseCase;
import com.barberia.userservice.domain.usecase.FindUserUseCase;
import com.barberia.userservice.domain.usecase.UpdateUserUserCase;
import com.barberia.userservice.domain.usecase.UserUseCase;
import com.barberia.userservice.infrastrcuture.entrypoint.dto.UserRequestDto;
import com.barberia.userservice.infrastrcuture.entrypoint.mapper.UserMapper;
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
    private final UserUseCase useCase;
    private final FindUserUseCase findUser;
    private final UpdateUserUserCase updateUserUserCase;
    private final DeleteUserUseCase deleteUserUseCase;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(UserRequestDto.class)
                .flatMap(user -> {
                    LOGGER.info(user.toString());
                    return this.useCase.save(UserMapper.toUser(user)).flatMap(ServerResponse.created(request.uri())::bodyValue);});
    }

    public Mono<ServerResponse> findByUsername(ServerRequest request){
        return this.findUser.findByUsername(request.pathVariable("username"))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        return this.findUser.findById(request.pathVariable("id"))
                .flatMap(ServerResponse.ok()::bodyValue);

    }

    public Mono<ServerResponse> update(ServerRequest request){
        return request.bodyToMono(UserRequestDto.class)
                .flatMap(user -> this.updateUserUserCase.updateUser(request.pathVariable("id"), UserMapper.toUser(user)))
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        return this.deleteUserUseCase.deleteUser(request.pathVariable("username"))
                .flatMap(user -> ServerResponse.noContent().build());

    }

}
