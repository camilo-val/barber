package com.barberia.employedservice.domain.model.gateway;

import com.barberia.employedservice.domain.model.Employed;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployedGateway {
    Mono<Employed> save(Employed employed);
    Mono<Employed> findById(String id);
    Mono<Employed> findByDocument(String document);
    Mono<Void> delete(Employed employed);
    Flux<Employed> findAll();
}
