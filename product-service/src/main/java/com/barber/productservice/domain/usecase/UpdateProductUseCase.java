package com.barber.productservice.domain.usecase;

import com.barber.productservice.domain.model.Product;
import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.domain.model.exceptions.BusinessMessageExceptions;
import com.barber.productservice.domain.model.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductGateway productGateway;

    public Mono<Product> update(String id, Product product){
        return productIsValid(product.getSku(), id)
                .flatMap(isValid -> {
                    if (!isValid){
                        return Mono.error(() -> new BusinessExceptions(BusinessMessageExceptions.PRODUCT_EXIST));
                    }
                    Product updateProduct = Product.newProduct(id,product.getProductName(),
                            product.getSku(),
                            product.getStatus(), product.getPrice(), product.getCreateAt(),
                            product.getCategoryId());
                    return this.productGateway.update(updateProduct);
                });
    }

    private Mono<Boolean> productIsValid(String sku,String id){
        return productExist(sku).zipWhen( exist -> productGateway.findBySku(sku))
                .map(tuple -> {
                    if(tuple.getT1()){
                        return tuple.getT2().getId().equals(id);
                    }
                    return true;
                });
    }

    private Mono<Boolean> productExist(String sku){
        return this.productGateway.findBySku(sku).hasElement();
    }
}
