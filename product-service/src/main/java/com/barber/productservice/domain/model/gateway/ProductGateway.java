package com.barber.productservice.domain.model.gateway;

import com.barber.productservice.domain.model.Product;
import reactor.core.publisher.Mono;

public interface ProductGateway {
    Mono<Product> create(Product product);
    Mono<Product> update(Product product);
    Mono<Product> findBySku(String sku);
    Mono<Void> deleteBySku(String sku);
}
