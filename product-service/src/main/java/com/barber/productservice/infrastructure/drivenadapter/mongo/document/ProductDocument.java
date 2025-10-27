package com.barber.productservice.infrastructure.drivenadapter.mongo.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDocument {
    @Id
    private String id;
    private String productName;
    private String sku;
    private String status;
    private Double price;
    private Date createAt;
    private String categoryId;
}
