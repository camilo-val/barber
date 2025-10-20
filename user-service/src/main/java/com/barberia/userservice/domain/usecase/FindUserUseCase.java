package com.barberia.userservice.domain.usecase;

import com.barberia.userservice.domain.model.User;
import com.barberia.userservice.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindUserUseCase {
    private final UserGateway userGateway;

    public Mono<User> findByUsername(String username){
        return this.userGateway.findByUsername(username);
    }

    public Mono<User> findById(String id){
        return this.userGateway.findById(id);
    }

}
