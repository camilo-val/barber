package com.barber.transactional.domain.usecase.transaction;

import com.barber.transactional.domain.model.employed.EmployedGateway;

import com.barber.transactional.domain.model.exceptions.BusinessExceptions;
import com.barber.transactional.domain.model.exceptions.BusinessExceptionsMessage;
import com.barber.transactional.domain.model.product.gateway.ProductGateway;
import com.barber.transactional.domain.model.transaction.Transaction;
import com.barber.transactional.domain.model.transaction.gateway.TransactionGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateTransactionUseCase {
    private final EmployedGateway employedGateway;
    private final ProductGateway productGateway;
    private final TransactionGateway transactionGateway;

    public Mono<Transaction> createTransaction(Transaction transaction){
        return Mono.zip(employedIsValid(transaction.getEmployed()), productIsValid(transaction.getProduct()))
                .flatMap(tuple -> {
                    if(!tuple.getT1() || !tuple.getT2()){
                        return Mono.error(() -> new BusinessExceptions(BusinessExceptionsMessage.TRANSACTION_NOT_SUPPORTED));
                    }

                     Transaction newTransaction = Transaction.newTransaction(transaction.getId(),transaction.getClient(),
                                transaction.getEmployed(),transaction.getProduct(),
                                transaction.getPrice(),transaction.getCreateAt(),
                                transaction.getStatus());
                    return this.transactionGateway.create(newTransaction);});
    }

    private Mono<Boolean> employedIsValid(String id){
        return employedGateway.findById(id).hasElement();
    }

    private Mono<Boolean> productIsValid(String id){
        return productGateway.findBySku(id).hasElement();
    }
}
