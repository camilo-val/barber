package com.barberia.employedservice.infrastructure.drivenadapter.restconsumer.rest;

import com.barberia.employedservice.domain.model.User;
import com.barberia.employedservice.domain.model.gateway.UserGateway;
import com.barberia.employedservice.infrastructure.drivenadapter.restconsumer.dto.UserRequestDto;
import com.barberia.employedservice.infrastructure.drivenadapter.restconsumer.dto.UserResponseDto;
import com.barberia.employedservice.infrastructure.drivenadapter.restconsumer.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRestConsumer implements UserGateway {

    private final WebClient.Builder builder;
    private final GlobalErrorClient globalErrorClient;

    @Override
    public Mono<User> save(User user) {
        return this.builder.build()
                .post()
                .uri("/api/user-service/create")
                .bodyValue(UserMapper.toUserRequestDto(user))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, globalErrorClient.handle())
                .bodyToMono(UserResponseDto.class)
                .map(UserMapper::toUser);
    }

    @Override
    public Mono<User> update(String id, User user) {
        return null;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return this.builder.build()
                .get()
                .uri("/api/user-service/{username}", username)
                .retrieve()
                .onStatus(HttpStatusCode::isError, globalErrorClient.handle())
                .bodyToMono(UserResponseDto.class)
                .map(UserMapper::toUser);
    }

    @Override
    public Mono<Void> delete(User user) {
        return null;
    }
}
