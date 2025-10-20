package com.clientservice.infrastructure.drivenadapter.mongo.data;

import com.clientservice.infrastructure.drivenadapter.mongo.document.ClientDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepositoryData extends ReactiveMongoRepository<ClientDocument,String> {
    Mono<ClientDocument> findByDocument(String document);
}
