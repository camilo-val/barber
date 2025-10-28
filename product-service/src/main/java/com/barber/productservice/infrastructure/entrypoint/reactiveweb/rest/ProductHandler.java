package com.barber.productservice.infrastructure.entrypoint.reactiveweb.rest;

import com.barber.productservice.domain.usecase.product.CreateProductUseCase;
import com.barber.productservice.domain.usecase.product.DeleteProductUseCase;
import com.barber.productservice.domain.usecase.product.FindProductUseCase;
import com.barber.productservice.domain.usecase.product.UpdateProductUseCase;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.product.ProductRequestDto;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.product.ProductResponseDto;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception.TechnicalExceptions;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception.TechnicalExceptionsMessage;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ProductHandler {
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final FindProductUseCase findProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ProductMapper productMapper;

    public Mono<ServerResponse> create (ServerRequest request){
        return request.bodyToMono(ProductRequestDto.class)
                .flatMap(productRequestDto -> createProductUseCase.create(productMapper.toDomain(productRequestDto)))
                .map(productMapper::toResponse)
                .flatMap(response -> ServerResponse.created(request.uri()).bodyValue(response))
                .switchIfEmpty(Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.BAD_REQUEST)));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        return request.bodyToMono(ProductRequestDto.class)
                .flatMap(productRequestDto ->
                        updateProductUseCase.update(request.pathVariable("sku"),productMapper.toDomain(productRequestDto)))
                .map(productMapper::toResponse)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .switchIfEmpty(Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.BAD_REQUEST)));
    }

    public Mono<ServerResponse> findBySku(ServerRequest request){
        return this.findProductUseCase.findBySku(request.pathVariable("sku"))
                .map(productMapper::toResponse)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> delete (ServerRequest request){
        return this.deleteProductUseCase.deleteBySku(request.pathVariable("sku"))
                .flatMap(response -> ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> findByCategory(ServerRequest request){
        Flux<ProductResponseDto> products = this.findProductUseCase
                .findByCategory(request.pathVariable("category"))
                .map(productMapper::toResponse);

        return ServerResponse.ok().body(products, ProductResponseDto.class);

    }

}
