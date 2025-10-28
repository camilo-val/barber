package com.barber.transactional.domain.usecase.transaction;

import com.barber.transactional.domain.model.transaction.Transaction;
import com.barber.transactional.domain.model.transaction.gateway.TransactionGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindTransactionUseCase {
    private final TransactionGateway transactionGateway;

    public Mono<Transaction> findById(String id){
        return transactionGateway.findById(id);

    }

    public Flux<Transaction> findByClient(String id){
        return transactionGateway.findByClient(id);
    }

    public Flux<Transaction> findByProduct(String id){
        return transactionGateway.findByProduct(id);
    }

    public Flux<Transaction> findByEmployed(String id){
        return transactionGateway.findByEmployed(id);
    }
}
