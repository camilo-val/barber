package com.barber.transactional.infrastructure.drivenadapter.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDocument {
    private String id;
    private String client;
    private String employed;
    private String product;
    private double price;
    private Date createAt;
    private String status;
}
