package com.barber.productservice.domain.usecase;

import com.barber.productservice.domain.model.Product;
import com.barber.productservice.domain.model.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindProductUseCase {
    private final ProductGateway productGateway;

    public Mono<Product> findBySku(String sku){
        return this.productGateway.findBySku(sku);
    }
}
