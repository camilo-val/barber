package com.barberia.userservice.infrastrcuture.config;

import com.barberia.userservice.domain.model.gateway.UserGateway;
import com.barberia.userservice.domain.usecase.FindUserUseCase;
import com.barberia.userservice.domain.usecase.UpdateUserUserCase;
import com.barberia.userservice.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    private final String url = "http://localhost:8080";
    @Bean
    public UserUseCase useCase(UserGateway userGateway){
        return new UserUseCase(userGateway);
    }

    @Bean
    public FindUserUseCase findUserUseCase(UserGateway userGateway){
        return new FindUserUseCase(userGateway);
    }

    @Bean
    public UpdateUserUserCase updateUserUserCase (UserGateway userGateway){
        return new UpdateUserUserCase(userGateway);

    }
}
