package com.barberia.employedservice.domain.usecase;

import com.barberia.employedservice.domain.model.Employed;
import com.barberia.employedservice.domain.model.gateway.EmployedGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateEmployedUseCase {
    private final EmployedGateway employedGateway;

    public Mono<Employed> update(Employed employed){
        return this.employedGateway.save(employed);
    }
}
