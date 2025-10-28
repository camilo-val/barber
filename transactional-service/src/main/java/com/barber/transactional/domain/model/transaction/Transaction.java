package com.barber.transactional.domain.model.transaction;

import com.barber.transactional.domain.model.exceptions.BusinessExceptions;
import com.barber.transactional.domain.model.exceptions.BusinessExceptionsMessage;
import lombok.Getter;

import java.util.Date;

@Getter
public final class Transaction {

    private final String id;
    private final String client;
    private final String employed;
    private final String product;
    private final double price;
    private final Date createAt;
    private final String status;

    private Transaction(String id, String client, String employed, String product, double price, Date createAt, String status) {
        this.id = id;
        this.client = client;
        this.employed = employed;
        this.product = product;
        this.price = price;
        this.createAt = createAt;
        this.status = status;
    }

    public static Transaction newTransaction(String id, String client, String employed,
                                             String product, double price, Date createAt, String status){
        if(client == null || client.isBlank() || employed == null || employed.isBlank() ||
                product == null || product.isBlank() ||
                price < 0 || status == null || status.isBlank()){
            throw new BusinessExceptions(BusinessExceptionsMessage.TRANSACTION_NOT_SUPPORTED);
        }
        return new Transaction(id, client, employed, product, price, createAt, status);
    }

}
