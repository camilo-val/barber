package com.barber.transactional.domain.model.transaction.gateway;

import com.barber.transactional.domain.model.transaction.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionGateway {
    Mono<Transaction> create(Transaction transaction);
    Mono<Transaction> findById(String id);
    Flux<Transaction> findByClient(String id);
    Flux<Transaction> findByProduct (String id);
    Flux<Transaction> findByEmployed (String id);
}
