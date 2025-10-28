package com.barber.transactional.infrastructure.drivenadapter.mongo.data;

import com.barber.transactional.infrastructure.drivenadapter.mongo.model.TransactionDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionData extends ReactiveMongoRepository<TransactionDocument,String> {

    Flux<TransactionDocument> findByClient(String client);
    Flux<TransactionDocument> findByProduct (String product);
    Flux<TransactionDocument> findByEmployed (String employed);
}
