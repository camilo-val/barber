package com.barber.transactional.domain.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Product {

    private final String id;
    private final String productName;
    private final String sku;
    private final String status;
    private final Double price;
    private final Date createAt;
    private final String categoryId;
}
