package com.barberia.userservice.infrastrcuture.drivenadapter.adapter;

import com.barberia.userservice.domain.model.User;
import com.barberia.userservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.userservice.domain.model.exceptions.message.BusinessMessageException;
import com.barberia.userservice.domain.model.gateway.UserGateway;
import com.barberia.userservice.infrastrcuture.drivenadapter.mapper.UserMapper;
import com.barberia.userservice.infrastrcuture.drivenadapter.springdata.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements UserGateway {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserRepository repository;
    @Override
    public Mono<User> save(User newUser) {
        LOGGER.info("ENTRE ADAPTER");
        return this.repository.save(UserMapper.toEntity(newUser)).map(UserMapper::toUser);
    }

    @Override
    public Mono<User> update(String id, User updateUser) {
        User user = User.updateUser(id,updateUser.getUsername(),updateUser.getPassword(),updateUser.getStatus(),updateUser.getRole(),updateUser.getUpdateAt());
        return this.repository.save(UserMapper.toEntity(user)).map(UserMapper::toUser);
    }

    @Override
    public Mono<User> findById(String id) {
        return this.repository.findById(id).map(UserMapper::toUser).switchIfEmpty(Mono.error(new BusinessExceptions(BusinessMessageException.USER_NOT_EXIST)));
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return this.repository.findByUsername(username).map(UserMapper::toUser)
                .switchIfEmpty(Mono.error(new BusinessExceptions(BusinessMessageException.USER_NOT_EXIST)));
    }



    @Override
    public Mono<Boolean> userExist(String username) {
        return this.repository.findByUsername(username).hasElement();
    }


}
