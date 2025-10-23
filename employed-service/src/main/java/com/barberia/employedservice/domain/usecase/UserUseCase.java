package com.barberia.employedservice.domain.usecase;

import com.barberia.employedservice.domain.model.User;
import com.barberia.employedservice.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;

    public Mono<User> save(User user){
        return this.userGateway.save(user);
    }

    public Mono<User> update(String id, User user){
        return this.userGateway.update(id, user);
    }

    public Mono<User> findByUsername(String username){
        return this.userGateway.findByUsername(username);
    }
}
