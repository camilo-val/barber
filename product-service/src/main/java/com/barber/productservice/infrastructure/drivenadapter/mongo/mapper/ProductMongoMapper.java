package com.barber.productservice.infrastructure.drivenadapter.mongo.mapper;

import com.barber.productservice.domain.model.Product;
import com.barber.productservice.infrastructure.drivenadapter.mongo.document.ProductDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface ProductMongoMapper {

    Product toDomain(ProductDocument document);
    ProductDocument toDocument(Product product);

    @ObjectFactory
    default Product createProduct(ProductDocument document) {
        if (document == null) return null;
        return Product.newProduct(
                document.getId(),
                document.getProductName(),
                document.getSku(),
                document.getStatus(),
                document.getPrice(),
                document.getCreateAt(),
                document.getCategoryId()
        );
    }
}
