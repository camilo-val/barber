package com.barber.productservice.domain.usecase.category;

import com.barber.productservice.domain.model.category.Category;
import com.barber.productservice.domain.model.category.gateway.CategoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindCategoryUseCase {
    private final CategoryGateway categoryGateway;

    public Mono<Category> findById(String id){
        return this.categoryGateway.findById(id);
    }

    public Mono<Category> findByName(String name){
        return this.categoryGateway.findByName(name);
    }

    public Flux<Category> findByStatus(boolean status){
        return this.categoryGateway.findByStatus(status);

    }
}
