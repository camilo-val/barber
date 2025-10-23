package com.barberia.employedservice.infrastructure.entrypoint.reativeweb.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterRest {
    private final String BASE = "/api/employed-service";
    @Bean
    public RouterFunction<ServerResponse> ruoter(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(BASE),handler::create);
    }
}
