package com.barberia.userservice.domain.usecase;

import com.barberia.userservice.domain.model.User;
import com.barberia.userservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.userservice.domain.model.exceptions.message.BusinessMessageException;
import com.barberia.userservice.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateUserUserCase {
    private final UserGateway userGateway;

    public Mono<User> updateUser(String id, User user){
        return Mono.zip(this.userGateway.userExist(user.getUsername())
                , this.userGateway.findById(id))
                .flatMap(tuple -> {
                    if(tuple.getT1()){
                        if (tuple.getT2().getId().equals(id) && !tuple.getT2().getUsername().equals(user.getUsername())){
                            throw new BusinessExceptions(BusinessMessageException.USER_EXIST);
                        }
                    }
                    User updateUSer = User.updateUser(id, user.getUsername(), user.getPassword(), user.getStatus(), user.getRole(), user.getUpdateAt());
                    return this.userGateway.update(id,updateUSer);
                });
    }
}
