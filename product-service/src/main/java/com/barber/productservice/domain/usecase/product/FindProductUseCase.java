package com.barber.productservice.domain.usecase.product;

import com.barber.productservice.domain.model.product.Product;
import com.barber.productservice.domain.model.product.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindProductUseCase {
    private final ProductGateway productGateway;

    public Mono<Product> findBySku(String sku){
        return this.productGateway.findBySku(sku);
    }

    public Flux<Product> findByCategory(String id){
        return this.productGateway.findByCategoryId(id);
    }

}
