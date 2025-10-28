package com.barber.productservice.infrastructure.drivenadapter.mongo.adapter;

import com.barber.productservice.domain.model.category.Category;
import com.barber.productservice.domain.model.category.gateway.CategoryGateway;
import com.barber.productservice.infrastructure.drivenadapter.mongo.data.CategoryRepository;
import com.barber.productservice.infrastructure.drivenadapter.mongo.mapper.CategoryMongoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryGateway {

    private final CategoryRepository repository;
    private final CategoryMongoMapper categoryMongoMapper;

    @Override
    public Mono<Category> create(Category category) {
        return repository.save(categoryMongoMapper.toDocument(category))
                .map(categoryMongoMapper::toDomain);
    }

    @Override
    public Mono<Category> findById(String id) {
        return repository.findById(id)
                .map(categoryMongoMapper::toDomain);
    }

    @Override
    public Mono<Category> findByName(String categoryName) {
        return repository.findByCategoryName(categoryName).map(categoryMongoMapper::toDomain);
    }

    @Override
    public Flux<Category> findByStatus(boolean status) {
        return repository.findByStatus(status).map(categoryMongoMapper::toDomain);
    }
}
