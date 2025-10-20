package com.clientservice.domain.model.gateway;

import com.clientservice.domain.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientGateway {
    Mono<Client> save(Client client);
    Mono<Client> findById(String id);
    Mono<Client> findByDocument(String document);
    Mono<Void> delete(Client client);
    Mono<Boolean> clientExist(String document);
    Flux<Client> findAll();
}
