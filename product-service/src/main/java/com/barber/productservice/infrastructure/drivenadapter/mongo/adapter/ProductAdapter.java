package com.barber.productservice.infrastructure.drivenadapter.mongo.adapter;

import com.barber.productservice.domain.model.product.Product;
import com.barber.productservice.domain.model.product.gateway.ProductGateway;
import com.barber.productservice.infrastructure.drivenadapter.mongo.data.ProductRepository;
import com.barber.productservice.infrastructure.drivenadapter.mongo.mapper.ProductMongoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ProductGateway {
    private final ProductRepository productRepository;
    private final ProductMongoMapper productMapper;

    @Override
    public Mono<Product> create(Product product) {
        return this.productRepository.save(productMapper.toDocument(product))
                .map(productMapper::toDomain);
    }

    @Override
    public Mono<Product> update(Product product) {
        return this.productRepository.save(productMapper.toDocument(product))
                .map(productMapper::toDomain);
    }

    @Override
    public Mono<Product> findBySku(String sku) {
        return this.productRepository.findBySku(sku)
                .map(productMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteBySku(String sku) {
        return this.productRepository.deleteBySku(sku);
    }

    @Override
    public Flux<Product> findByCategoryId(String categoryId) {
        return this.productRepository.findByCategoryId(categoryId);
    }
}
