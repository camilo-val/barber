package com.clientservice.infrastructure.drivenadapter.restconsumer.rest;

import com.clientservice.domain.model.User;
import com.clientservice.domain.model.gateway.UserGateway;
import com.clientservice.infrastructure.drivenadapter.restconsumer.dto.UserResponseDto;
import com.clientservice.infrastructure.drivenadapter.restconsumer.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class  UserRestConsumer  implements UserGateway {
    private final WebClient.Builder builder;
    private final ExternalErrorMapper externalErrorMapper;

    @Override
    public Mono<User> findByUsername(String username){
        return this.builder.build().get()
                .uri("/api/user-service/username/{username}", username)
                .retrieve()
                .bodyToMono(UserResponseDto.class)
                .map(UserMapper::toUser);
    }

    @Override
    public Mono<User> create(User user) {
        return builder.build()
                .post()
                .uri("/api/user-service/create")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(UserMapper.toUserRequestDto(user))
                .retrieve()
                .onStatus(HttpStatusCode::isError, externalErrorMapper.handleError("user-service"))
                .bodyToMono(UserResponseDto.class)
                .map(UserMapper::toUser);
    }

    @Override
    public Mono<User> update(String id, User user) {
        return builder.build()
                .put()
                .uri("/api/user-service/{id}", id)
                .bodyValue(UserMapper.toUserRequestDto(user))
                .retrieve()
                .bodyToMono(UserResponseDto.class)
                .map(UserMapper::toUser);
    }

    @Override
    public Mono<Void> deleteByUsername(String username) {
        return builder.build()
                .delete()
                .uri("/api/user-service/{username}", username)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
