package com.barber.productservice.infrastructure.drivenadapter.mongo.data;

import com.barber.productservice.infrastructure.drivenadapter.mongo.document.CategoryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveMongoRepository<CategoryDocument, String> {
    Mono<CategoryDocument> findByCategoryName(String categoryName);
    Flux<CategoryDocument> findByStatus(boolean status);

}
