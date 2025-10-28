package com.barber.productservice.domain.model.product.gateway;

import com.barber.productservice.domain.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductGateway {
    Mono<Product> create(Product product);
    Mono<Product> update(Product product);
    Mono<Product> findBySku(String sku);
    Mono<Void> deleteBySku(String sku);
    Flux<Product> findByCategoryId(String categoryId);
}
