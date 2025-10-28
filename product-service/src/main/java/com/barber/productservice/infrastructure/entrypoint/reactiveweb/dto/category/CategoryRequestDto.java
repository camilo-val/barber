package com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@ToString
public class CategoryRequestDto {
    private final String id;
    private final String categoryName;
    private final boolean status;
    private final Date createAt;
}
