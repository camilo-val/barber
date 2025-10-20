package com.clientservice.domain.usercase;

import com.clientservice.domain.model.Client;
import com.clientservice.domain.model.exceptions.BusinessException;
import com.clientservice.domain.model.exceptions.message.BusinessMessageException;
import com.clientservice.domain.model.gateway.ClientGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindClient {
    private final ClientGateway clientGateway;

    public Mono<Client> findById(String id){
        return this.clientGateway.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessMessageException.CLIENT_NOT_EXIST)));
    }

    public Mono<Client> findByDocument(String document){
        return this.clientGateway.findByDocument(document)
                .switchIfEmpty(Mono.error(new BusinessException(BusinessMessageException.CLIENT_NOT_EXIST)));
    }
}
