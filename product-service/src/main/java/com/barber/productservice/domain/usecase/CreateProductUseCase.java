package com.barber.productservice.domain.usecase;

import com.barber.productservice.domain.model.Product;
import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.domain.model.exceptions.BusinessMessageExceptions;
import com.barber.productservice.domain.model.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductGateway productGateway;

    public Mono<Product> create(Product product){
        return productExist(product.getSku())
                .flatMap(exist -> {
                    if (exist) {
                        return Mono.error(() -> new BusinessExceptions(BusinessMessageExceptions.PRODUCT_EXIST));
                    }
                    Product newProduct = Product.newProduct(product.getId(), product.getProductName(),
                            product.getSku(), product.getStatus(), product.getPrice(),
                            product.getCreateAt(), product.getCategoryId());
                    return productGateway.create(product);
                });//.onErrorMap(ex -> new BusinessExceptions(BusinessMessageExceptions.INVALID_PRODUCT,ex));
    }

    private Mono<Boolean> productExist(String sku){
        return productGateway.findBySku(sku).hasElement();
    }
}
