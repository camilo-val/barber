package com.clientservice.infrastructure.entrypoint.reactiveweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterRest {

    private final String BASE = "/api/client-service";

    @Bean
    public RouterFunction<ServerResponse> rout(Handler handler){
        return RouterFunctions.route(RequestPredicates.GET(BASE),handler::findById)
                .andRoute(RequestPredicates.POST(BASE),handler::createClient);
    }
}
