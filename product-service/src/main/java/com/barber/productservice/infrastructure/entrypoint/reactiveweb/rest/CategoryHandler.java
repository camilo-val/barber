package com.barber.productservice.infrastructure.entrypoint.reactiveweb.rest;

import com.barber.productservice.domain.usecase.category.CreateCategoryUseCase;
import com.barber.productservice.domain.usecase.category.FindCategoryUseCase;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.category.CategoryRequestDto;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.category.CategoryResponseDto;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception.TechnicalExceptions;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.exception.TechnicalExceptionsMessage;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryHandler {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final FindCategoryUseCase findCategoryUseCase;
    private final CategoryMapper categoryMapper;

    public Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(CategoryRequestDto.class)
                .flatMap(categoryRequest -> this.createCategoryUseCase
                        .createCategory(categoryMapper.createCategory(categoryRequest)))
                .map(categoryMapper::toResponse)
                .flatMap(response -> ServerResponse.created(request.uri()).bodyValue(response))
                .switchIfEmpty(Mono.error(() -> new TechnicalExceptions(TechnicalExceptionsMessage.BAD_REQUEST)));
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        return this.findCategoryUseCase.findById(request.pathVariable("id"))
                .map(categoryMapper::toResponse)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> findByCategoryName(ServerRequest request){
        return this.findCategoryUseCase.findByName(request.pathVariable("categoryName"))
                .map(categoryMapper::toResponse)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> findByStatus(ServerRequest request){
        Flux<CategoryResponseDto> categories =  this.findCategoryUseCase
                .findByStatus(Boolean.getBoolean(request.pathVariable("status")))
                .map(categoryMapper::toResponse);
        return ServerResponse.ok().body(categories, CategoryResponseDto.class);
    }
}
