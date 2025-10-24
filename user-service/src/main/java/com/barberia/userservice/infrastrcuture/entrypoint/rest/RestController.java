package com.barberia.userservice.infrastrcuture.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RestController {

    private final String BASE = "/api/user-service";
    private final GlobalHandlerError globalHandlerError;

    @Bean
    public RouterFunction<ServerResponse> routerFunctions(Handler handler){
        return RouterFunctions.route(RequestPredicates.POST(BASE + "/create"), handler::create)
                .andRoute(RequestPredicates.GET(BASE + "/username/{username}"), handler::findByUsername)
                .andRoute(RequestPredicates.GET(BASE + "/{id}"), handler::findById)
                .andRoute(RequestPredicates.PUT(BASE + "/{id}"),handler::update)
                .andRoute(RequestPredicates.DELETE(BASE + "/{username}"),handler::delete);
    }
}
