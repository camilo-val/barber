package com.barber.productservice.domain.model.category.gateway;

import com.barber.productservice.domain.model.category.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryGateway {
    Mono<Category> create(Category category);
    Mono<Category> findById(String id);
    Mono<Category> findByName(String categoryName);
    Flux<Category> findByStatus(boolean status);

}
