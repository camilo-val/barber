package com.barber.productservice.infrastructure.entrypoint.reactiveweb.mapper;

import com.barber.productservice.domain.model.product.Product;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.product.ProductRequestDto;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.product.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product toDomain(ProductRequestDto dto);
    ProductResponseDto toResponse(Product product);
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
