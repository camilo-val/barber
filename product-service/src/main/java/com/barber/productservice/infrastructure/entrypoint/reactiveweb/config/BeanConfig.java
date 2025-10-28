package com.barber.productservice.infrastructure.entrypoint.reactiveweb.config;

import com.barber.productservice.domain.model.category.gateway.CategoryGateway;
import com.barber.productservice.domain.model.product.gateway.ProductGateway;
import com.barber.productservice.domain.usecase.category.CreateCategoryUseCase;
import com.barber.productservice.domain.usecase.category.FindCategoryUseCase;
import com.barber.productservice.domain.usecase.product.CreateProductUseCase;
import com.barber.productservice.domain.usecase.product.DeleteProductUseCase;
import com.barber.productservice.domain.usecase.product.FindProductUseCase;
import com.barber.productservice.domain.usecase.product.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public CreateProductUseCase createProductUseCase(ProductGateway productGateway, CategoryGateway categoryGateway){
        return new CreateProductUseCase(productGateway, categoryGateway);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductGateway productGateway){
        return new DeleteProductUseCase(productGateway);
    }

    @Bean
    public FindProductUseCase findProductUseCase(ProductGateway productGateway){
        return new FindProductUseCase(productGateway);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductGateway productGateway){
        return new UpdateProductUseCase(productGateway);
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryGateway categoryGateway){
        return new CreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public FindCategoryUseCase findCategoryUseCase(CategoryGateway categoryGateway){
        return new FindCategoryUseCase(categoryGateway);

    }
}
