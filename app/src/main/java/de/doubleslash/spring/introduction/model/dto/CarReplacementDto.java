package de.doubleslash.spring.introduction.model.dto;

import de.doubleslash.spring.introduction.model.Car;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarReplacementDto {

    @NotNull(message = "Brand is required")
    @NotBlank(message = "Brand can not be a blank value")
    private String brand;

    @NotNull(message = "Model is required")
    @NotBlank(message = "Model can not be a blank value")
    private String model;

    public Car toCar() {
        return Car.builder().brand(brand).model(model).build();
    }

}

