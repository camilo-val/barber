package com.barberia.employedservice.infrastructure.drivenadapter.mongo.adapters;

import com.barberia.employedservice.domain.model.Employed;
import com.barberia.employedservice.domain.model.gateway.EmployedGateway;
import com.barberia.employedservice.infrastructure.drivenadapter.mongo.data.EmployedRepository;
import com.barberia.employedservice.infrastructure.drivenadapter.mongo.mapper.EmployedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployedAdapter implements EmployedGateway {

    private final EmployedRepository employedRepository;

    @Override
    public Mono<Employed> save(Employed employed) {
        return this.employedRepository.save(EmployedMapper.toEmployedDocument(employed))
                .map(EmployedMapper::toEmployed);
    }

    @Override
    public Mono<Employed> findById(String id) {
        return this.employedRepository.findById(id)
                .map(EmployedMapper::toEmployed);
    }

    @Override
    public Mono<Employed> findByDocument(String document) {
        return this.employedRepository.findByDocument(document)
                .map(EmployedMapper::toEmployed);
    }

    @Override
    public Mono<Void> delete(Employed employed) {
        return Mono.empty();
    }

    @Override
    public Flux<Employed> findAll() {
        return this.employedRepository.findAll().map(EmployedMapper::toEmployed);
    }
}
