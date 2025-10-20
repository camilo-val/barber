package com.barberia.userservice.domain.model.gateway;

import com.barberia.userservice.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<User> save(User newUser);
    Mono<User> update(String id, User updateUser);
    Mono<User> findById(String id);
    Mono<User> findByUsername(String username);
    Mono<Boolean> userExist(String username);
}
