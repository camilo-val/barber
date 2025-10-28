package com.barber.transactional.infrastructure.drivenadapter.mongo.adapter;

import com.barber.transactional.domain.model.transaction.Transaction;
import com.barber.transactional.domain.model.transaction.gateway.TransactionGateway;
import com.barber.transactional.infrastructure.drivenadapter.mongo.data.TransactionData;
import com.barber.transactional.infrastructure.drivenadapter.mongo.mapper.TransactionAdapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class TransactionAdapter implements TransactionGateway {
    private final TransactionData transactionData;
    private final TransactionAdapterMapper transactionAdapterMapper;


    @Override
    public Mono<Transaction> create(Transaction transaction) {
        return this.transactionData.save(transactionAdapterMapper.toDocument(transaction))
                .map(transactionAdapterMapper::toDomain);
    }

    @Override
    public Mono<Transaction> findById(String id) {
        return this.transactionData.findById(id)
                .map(transactionAdapterMapper::toDomain);
    }

    @Override
    public Flux<Transaction> findByClient(String id) {
        return this.transactionData.findByClient(id)
                .map(transactionAdapterMapper::toDomain);
    }

    @Override
    public Flux<Transaction> findByProduct(String id) {
        return this.transactionData.findByProduct(id)
                .map(transactionAdapterMapper::toDomain);
    }

    @Override
    public Flux<Transaction> findByEmployed(String id) {
        return this.transactionData.findByEmployed(id)
                .map(transactionAdapterMapper::toDomain);
    }
}