package com.barber.transactional.infrastructure.drivenadapter.mongo.mapper;


import com.barber.transactional.domain.model.transaction.Transaction;
import com.barber.transactional.infrastructure.drivenadapter.mongo.model.TransactionDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface TransactionAdapterMapper {
    Transaction toDomain(TransactionDocument transactionDocument);
    TransactionDocument toDocument(Transaction transaction);

    @ObjectFactory
    default Transaction createTransaction(TransactionDocument transactionDocument){
        if (transactionDocument == null) return null;
        return Transaction.newTransaction(transactionDocument.getId(),transactionDocument.getClient(),
                transactionDocument.getEmployed(),transactionDocument.getProduct(),
                transactionDocument.getPrice(),transactionDocument.getCreateAt(),
                transactionDocument.getStatus());

    }
}
