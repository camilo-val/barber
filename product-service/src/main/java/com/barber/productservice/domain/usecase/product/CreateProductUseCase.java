package com.barber.productservice.domain.usecase.product;

import com.barber.productservice.domain.model.category.gateway.CategoryGateway;
import com.barber.productservice.domain.model.product.Product;
import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.domain.model.exceptions.BusinessMessageExceptions;
import com.barber.productservice.domain.model.product.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductGateway productGateway;
    private final CategoryGateway categoryGateway;

    public Mono<Product> create(Product product){

        return Mono.zip(productExist(product.getSku()), categoryIsValid(product.getCategoryId()))
                .flatMap(tuple -> {
                    if (tuple.getT2()){
                        throw new BusinessExceptions(BusinessMessageExceptions.PRODUCT_EXIST);
                    }
                    if (!tuple.getT1()){
                        throw new BusinessExceptions(BusinessMessageExceptions.INVALID_CATEGORY);
                    }
                    Product newProduct = Product.newProduct(product.getId(), product.getProductName(),
                            product.getSku(), product.getStatus(), product.getPrice(),
                            product.getCreateAt(), product.getCategoryId());
                    return productGateway.create(product);
                });
    }

    private Mono<Boolean> productExist(String sku){
        return productGateway.findBySku(sku).hasElement();
    }

    private Mono<Boolean> categoryIsValid(String id){
        return this.categoryGateway.findById(id).hasElement();
    }
}
