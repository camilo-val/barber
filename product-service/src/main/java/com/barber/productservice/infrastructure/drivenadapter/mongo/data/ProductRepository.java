package com.barber.productservice.infrastructure.drivenadapter.mongo.data;

import com.barber.productservice.domain.model.product.Product;
import com.barber.productservice.infrastructure.drivenadapter.mongo.document.ProductDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<ProductDocument, String> {
    Mono<ProductDocument> findBySku(String sku);
    Mono<Void> deleteBySku(String sku);
    Flux<Product> findByCategoryId(String categoryId);
}
