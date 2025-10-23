package com.barberia.employedservice.infrastructure.config;

import com.barberia.employedservice.domain.model.gateway.EmployedGateway;
import com.barberia.employedservice.domain.model.gateway.UserGateway;
import com.barberia.employedservice.domain.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public CreateEmployedUseCase createEmployedUseCase(EmployedGateway employedGateway){
        return new CreateEmployedUseCase(employedGateway);

    }

    @Bean
    public EmployedUseCase employedUseCase(CreateEmployedUseCase createEmployedUseCase,
                                           UpdateEmployedUseCase updateEmployedUseCase,
                                           FindEmployedUseCase findEmployedUseCase,
                                           UserUseCase userUseCase){
        return new EmployedUseCase(createEmployedUseCase,updateEmployedUseCase,findEmployedUseCase,userUseCase);
    }

    @Bean
    public FindEmployedUseCase findEmployedUseCase(EmployedGateway employedGateway){
        return new FindEmployedUseCase(employedGateway);
    }

    @Bean
    public UpdateEmployedUseCase updateEmployedUseCase(EmployedGateway employedGateway){
        return new UpdateEmployedUseCase(employedGateway);
    }

    @Bean
    public UserUseCase userUseCase(UserGateway userGateway){
        return new UserUseCase(userGateway);
    }
}
