package com.barber.productservice.domain.usecase.category;

import com.barber.productservice.domain.model.category.Category;
import com.barber.productservice.domain.model.category.gateway.CategoryGateway;
import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.domain.model.exceptions.BusinessMessageExceptions;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCategoryUseCase {
    private final CategoryGateway categoryGateway;

    public Mono<Category> createCategory(Category category){
        return exist(category.getCategoryName()).flatMap( exist ->{
            if (exist){
                return Mono.error(() -> new BusinessExceptions(BusinessMessageExceptions.CATEGORY_EXIST));
            }
            Category newCategory = Category.newCategory(category.getId(),category.getCategoryName(),
                    category.getStatus(),
                    category.getCreateAt());
            return this.createCategory(newCategory);
        });

    }
    private Mono<Boolean> exist(String categoryName){
        return this.categoryGateway.findByName(categoryName).hasElement();
    }
}
