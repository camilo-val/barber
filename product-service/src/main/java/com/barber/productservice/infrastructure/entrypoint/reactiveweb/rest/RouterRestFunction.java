package com.barber.productservice.infrastructure.entrypoint.reactiveweb.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterRestFunction {
    private final String BASE = "/api/product-service";
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(BASE),handler::create);
    }
}
