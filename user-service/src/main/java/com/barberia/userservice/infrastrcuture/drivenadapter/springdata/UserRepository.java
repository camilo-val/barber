package com.barberia.userservice.infrastrcuture.drivenadapter.springdata;

import com.barberia.userservice.infrastrcuture.drivenadapter.document.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<UserEntity> findByUsername(String username);
    Mono<Void> deleteByUsername(String username);
}
