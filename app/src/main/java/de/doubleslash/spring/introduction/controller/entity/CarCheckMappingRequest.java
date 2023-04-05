package de.doubleslash.spring.introduction.controller.entity;

import org.springframework.validation.annotation.Validated;

@Validated
public class CarCheckMappingRequest extends CarRequest {

    public CarCheckMappingRequest(final Long id, final String model, final String brand){
        super(id, model, brand);
    }
}