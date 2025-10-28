package com.barber.transactional.domain.model.employed;

import reactor.core.publisher.Mono;

public interface EmployedGateway {
    Mono<Employed> findById(String id);
}
