package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.model.dto.CarReplacementDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarCheckMappingRequest {

    @NotNull(message = "Id field is required")
    @Min(value = 1, message = "Id has to be greater than 0")
    private Long idOfOldCar;

    @Valid
    @NotNull(message = "Data for the new car instance is required")
    private CarReplacementDto replacementDto;

}
