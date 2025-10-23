package com.barberia.employedservice.domain.usecase;

import com.barberia.employedservice.domain.model.Employed;
import com.barberia.employedservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.employedservice.domain.model.exceptions.BusinessExceptionsMessages;
import com.barberia.employedservice.domain.model.gateway.EmployedGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindEmployedUseCase {
    private final EmployedGateway employedGateway;

    public Mono<Employed> findById(String id){
        return employedGateway.findById(id)
                .switchIfEmpty(Mono.error(() -> new BusinessExceptions(BusinessExceptionsMessages.CLIENT_NOT_EXIST)));

    }

    public Mono<Employed> findByDocument(String document){
        return employedGateway.findById(document)
                .switchIfEmpty(Mono.error(() -> new BusinessExceptions(BusinessExceptionsMessages.CLIENT_NOT_EXIST)));
    }

    public Mono<Boolean> employedExist(String document){
        return employedGateway.findByDocument(document).hasElement();
    }
}
