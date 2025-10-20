package com.clientservice.domain.model.gateway;

import com.clientservice.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<User> findByUsername(String username);
    Mono<User> create(User user);
    Mono<User> update(String id, User user);
}
