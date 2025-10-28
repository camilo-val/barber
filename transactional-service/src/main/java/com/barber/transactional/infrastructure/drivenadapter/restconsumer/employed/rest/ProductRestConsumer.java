package com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.rest;

import com.barber.transactional.domain.model.product.Product;
import com.barber.transactional.domain.model.product.gateway.ProductGateway;
import com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.dto.ProductResponseDto;
import com.barber.transactional.infrastructure.drivenadapter.restconsumer.employed.mapper.ProductRestConsumerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductRestConsumer implements ProductGateway {
    private final WebClient.Builder productWebClient;
    private ProductRestConsumerMapper mapper;

    @Override
    public Mono<Product> findBySku(String sku) {
        return this.productWebClient.build()
                .get()
                .uri("/api/product-service/{sku}", sku)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductResponseDto.class)
                .map(mapper::toDomain);
    }


}
