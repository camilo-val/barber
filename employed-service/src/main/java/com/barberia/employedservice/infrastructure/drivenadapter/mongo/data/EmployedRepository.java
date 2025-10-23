package com.barberia.employedservice.infrastructure.drivenadapter.mongo.data;

import com.barberia.employedservice.infrastructure.drivenadapter.mongo.document.EmployedDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface EmployedRepository extends ReactiveMongoRepository<EmployedDocument,String> {
    Mono<EmployedDocument> findByDocument(String document);
}
