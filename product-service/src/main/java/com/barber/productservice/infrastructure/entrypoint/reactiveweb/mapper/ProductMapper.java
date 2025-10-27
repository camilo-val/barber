package com.barber.productservice.infrastructure.entrypoint.reactiveweb.mapper;

import com.barber.productservice.domain.model.Product;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.ProductRequestDto;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // 🔹 Request DTO → Domain
    Product toDomain(ProductRequestDto dto);

    // 🔹 Domain → Response DTO
    ProductResponseDto toResponse(Product product);

    // 🏭 Factory method: cómo construir el dominio correctamente
    @ObjectFactory
    default Product createProduct(ProductRequestDto dto) {
        if (dto == null) return null;
        return Product.newProduct(
                dto.getId(),
                dto.getProductName(),
                dto.getSku(),
                dto.getStatus(),
                dto.getPrice(),
                dto.getCreateAt(),
                dto.getCategoryId()
        );
    }
}
