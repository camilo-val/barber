package com.barberia.userservice.domain.usecase;

import com.barberia.userservice.domain.model.User;
import com.barberia.userservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.userservice.domain.model.exceptions.message.BusinessMessageException;
import com.barberia.userservice.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UserGateway userGateway;

    public Mono<Void> deleteUser(String username){
        return this.userGateway.userExist(username)
                .flatMap(exist -> !exist
                        ? Mono.error(() -> new BusinessExceptions(BusinessMessageException.USER_NOT_EXIST))
                        : this.userGateway.deleteByUsername(username));
    }
}
