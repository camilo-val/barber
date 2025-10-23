package com.barberia.employedservice.domain.usecase;

import com.barberia.employedservice.domain.model.Employed;
import com.barberia.employedservice.domain.model.gateway.EmployedGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateEmployedUseCase {
    private final EmployedGateway employedGateway;

    public Mono<Employed> create(Employed employed){
        return this.employedGateway.save(employed);
    }
}
