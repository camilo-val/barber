package com.barber.productservice.domain.model;

import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;

import java.util.Date;

@Getter
public class Product {
    private final String id;
    private final String productName;
    private final String sku;
    private final String status;
    private final Double price;
    private final Date createAt;
    private final String categoryId;

    private Product (String id, String productName,
                     String sku, String status, Double price,
                     Date createAt, String categoryId){
        this.id = id;
        this.productName = productName;
        this.sku = sku;
        this.status = status;
        this.price = price;
        this.createAt = createAt;
        this.categoryId = categoryId;
    }

    public static Product newProduct(String id, String productName,
                                     String sku, String status, Double price,
                                     Date createAt, String categoryId){
        if (productName == null || sku == null || categoryId == null){
            throw new BusinessExceptions(BusinessMessageExceptions.INVALID_PRODUCT);
        }
        return new Product(id,productName,sku,status,price,createAt,categoryId);
    }
}
