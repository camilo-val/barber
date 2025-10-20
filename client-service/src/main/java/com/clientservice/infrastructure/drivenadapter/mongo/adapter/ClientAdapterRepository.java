package com.clientservice.infrastructure.drivenadapter.mongo.adapter;

import com.clientservice.domain.model.Client;
import com.clientservice.domain.model.gateway.ClientGateway;
import com.clientservice.infrastructure.drivenadapter.mongo.data.ClientRepositoryData;
import com.clientservice.infrastructure.drivenadapter.mongo.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ClientAdapterRepository implements ClientGateway {
    private final ClientRepositoryData clientRepositoryData;

    @Override
    public Mono<Client> save(Client client) {
        return this.clientRepositoryData.save(ClientMapper.toDocument(client)).map(ClientMapper::toClient);
    }

    @Override
    public Mono<Client> findById(String id) {
        return this.clientRepositoryData.findById(id).map(ClientMapper::toClient);
    }

    @Override
    public Mono<Client> findByDocument(String document) {
        return this.clientRepositoryData.findByDocument(document).map(ClientMapper::toClient);
    }

    @Override
    public Mono<Void> delete(Client client) {
        return this.clientRepositoryData.delete(ClientMapper.toDocument(client));
    }

    @Override
    public Mono<Boolean> clientExist(String document) {
        return this.clientRepositoryData.findByDocument(document).hasElement();
    }

    @Override
    public Flux<Client> findAll() {
        return this.clientRepositoryData.findAll().map(ClientMapper::toClient);
    }
}
