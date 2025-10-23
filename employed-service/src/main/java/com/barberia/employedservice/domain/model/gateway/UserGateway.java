package com.barberia.employedservice.domain.model.gateway;

import com.barberia.employedservice.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<User> save(User user);
    Mono<User> update(String id, User user);
    Mono<User> findByUsername(String username);
    Mono<Void> delete(User user);
}
