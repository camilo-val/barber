package com.barberia.userservice.domain.usecase;

import com.barberia.userservice.domain.model.User;
import com.barberia.userservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.userservice.domain.model.exceptions.message.BusinessMessageException;
import com.barberia.userservice.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    public Mono<User> save(User user){
        return this.userGateway.userExist(user.getUsername())
                .flatMap(userBd -> {
                    if (userBd){
                        return Mono.error(new BusinessExceptions(BusinessMessageException.USER_EXIST));
                    }else {
                        return this.userGateway.save(user);
                    }
                });
    }

}
