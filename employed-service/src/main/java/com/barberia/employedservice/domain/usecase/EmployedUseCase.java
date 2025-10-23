package com.barberia.employedservice.domain.usecase;

import com.barberia.employedservice.domain.model.Employed;
import com.barberia.employedservice.domain.model.User;
import com.barberia.employedservice.domain.model.exceptions.BusinessExceptions;
import com.barberia.employedservice.domain.model.exceptions.BusinessExceptionsMessages;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EmployedUseCase {
    private final CreateEmployedUseCase createEmployedUseCase;
    private final UpdateEmployedUseCase updateEmployedUseCase;
    private final FindEmployedUseCase findEmployedUseCase;
    private final UserUseCase userUseCase;

    public Mono<Employed> createEmployed (Employed employed, User user){
        return Mono.zip(this.userUseCase.save(user),
                this.findEmployedUseCase.employedExist(employed.getDocument()))
                .flatMap(tuple -> tuple.getT2()
                                ? Mono.error(() -> new BusinessExceptions(BusinessExceptionsMessages.CLIENT_EXIST))
                                : Mono.just(Employed.createEmployed(employed.getId(), employed.getName(),
                        employed.getDocument(),employed.getEmail(), employed.getCellphone(),tuple.getT1().getId(),employed.getBirthdate()))

                )
                .flatMap(this.createEmployedUseCase::create);
    }

    public Mono<Employed> updateEmployed(String id, Employed employed, User user){
        return Mono.zip(this.userUseCase.update(user.getId(), user),
                this.findEmployedUseCase.employedExist(employed.getDocument()),
                        this.findEmployedUseCase.findById(id))
                .flatMap(tuple -> {
                    if (tuple.getT2()){
                        if (tuple.getT3().getId().equals(id) && !tuple.getT3().getDocument().equals(employed.getDocument())){
                            if (!tuple.getT3().getUserId().equals(user.getId())){
                                return Mono.error(() -> new BusinessExceptions(BusinessExceptionsMessages.INVALID_CLIENT));
                            }
                            return Mono.error(() -> new BusinessExceptions(BusinessExceptionsMessages.CLIENT_EXIST));
                        }
                    }
                    return Mono.just(Employed.createEmployed(employed.getId(),employed.getName(),
                            employed.getDocument(),employed.getEmail(),employed.getCellphone(),employed.getUserId(),employed.getBirthdate()));
                })
                .flatMap(this.updateEmployedUseCase::update);
    }
}
