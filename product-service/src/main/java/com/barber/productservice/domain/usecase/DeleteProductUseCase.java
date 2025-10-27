package com.barber.productservice.domain.usecase;

import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.domain.model.exceptions.BusinessMessageExceptions;
import com.barber.productservice.domain.model.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteProductUseCase {

    private final ProductGateway productGateway;

    public Mono<Void> deleteBySku(String sku){
        return this.productGateway.findBySku(sku)
                .flatMap(product -> this.productGateway.deleteBySku(product.getSku()))
                .switchIfEmpty(Mono.error(() -> new BusinessExceptions(BusinessMessageExceptions.PRODUCT_EXIST)));
    }

}
