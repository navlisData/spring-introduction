package de.doubleslash.spring.introduction.controller.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class CarRequest {
    private Long id;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotBlank(message = "Brand is mandatory")
    private String brand;

    public CarRequest(final Long id, final String model, final String brand) {
        this.id = id;
        this.model = model;
        this.brand = brand;
    }
}
