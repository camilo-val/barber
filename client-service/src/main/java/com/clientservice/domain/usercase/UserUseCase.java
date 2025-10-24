package com.clientservice.domain.usercase;

import com.clientservice.domain.model.User;
import com.clientservice.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;

    public Mono<User> findByUsername(String username){
        return this.userGateway.findByUsername(username);
    }

    public Mono<User> create(User user){
        return this.userGateway.create(user);
    }

    public Mono<User> update(String id, User user){
        return this.userGateway.update(id, user);
    }

    public Mono<Void> deleteByUsername(String username){
        return this.userGateway.deleteByUsername(username);

    }
}
