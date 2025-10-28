package com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
public class ProductResponseDto {
    private final String id;
    private final String productName;
    private final String sku;
    private final String status;
    private final Double price;
    private final Date createAt;
    private final String categoryId;
}
