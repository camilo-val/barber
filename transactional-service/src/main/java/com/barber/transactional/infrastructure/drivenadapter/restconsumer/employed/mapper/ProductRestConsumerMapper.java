package com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.mapper;

import com.barber.transactional.domain.model.product.Product;
import com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.dto.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestConsumerMapper {
    public Product toDomain(ProductResponseDto dto);
}
