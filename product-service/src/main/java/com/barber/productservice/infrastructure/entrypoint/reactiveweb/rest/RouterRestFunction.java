package com.barber.productservice.infrastructure.entrypoint.reactiveweb.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterRestFunction {
    private final String BASE = "/api/product-service";
    @Bean
    public RouterFunction<ServerResponse> routerFunction(ProductHandler handler,
                                                         CategoryHandler categoryHandler){
        return RouterFunctions.route(RequestPredicates.POST(BASE),handler::create)
                .andRoute(RequestPredicates.GET(BASE + "/category/{category}"),handler::findByCategory)
                .andRoute(RequestPredicates.GET(BASE + "/{sku}"),handler::findBySku)
                .andRoute(RequestPredicates.PUT(BASE + "/{sku}"),handler::update)
                .andRoute(RequestPredicates.DELETE(BASE + "/{sku}"),handler::delete)
                .andRoute(RequestPredicates.POST(BASE + "/category"),categoryHandler::create)
                .andRoute(RequestPredicates.GET(BASE + "/category/{id}"), categoryHandler::findById)
                .andRoute(RequestPredicates.GET(BASE + "/category/name/{categoryName}"), categoryHandler::findByCategoryName)
                .andRoute(RequestPredicates.GET(BASE + "/category/status/{status}"), categoryHandler::findByStatus);
    }
}
