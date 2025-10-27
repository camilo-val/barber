package com.barber.productservice.infrastructure.entrypoint.reactiveweb.config;

import com.barber.productservice.domain.model.gateway.ProductGateway;
import com.barber.productservice.domain.usecase.CreateProductUseCase;
import com.barber.productservice.domain.usecase.DeleteProductUseCase;
import com.barber.productservice.domain.usecase.FindProductUseCase;
import com.barber.productservice.domain.usecase.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public CreateProductUseCase createProductUseCase(ProductGateway productGateway){
        return new CreateProductUseCase(productGateway);
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
}
