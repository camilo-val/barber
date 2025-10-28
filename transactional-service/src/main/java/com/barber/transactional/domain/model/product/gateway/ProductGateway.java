package com.barber.transactional.domain.model.product.gateway;

import com.barber.transactional.domain.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductGateway {
    Mono<Product> findBySku(String sku);

}
