package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.repository.CarRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class CarDealershipController {
//    private final CarRepository repository;

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> all() {
        throw new NotYetImplementedException();
    }

    @GetMapping(value = "/car/{id}")
    public Car get(@PathVariable Long id) {
        throw new NotYetImplementedException();
    }

    @Valid
    @NotNull
    @RequestBody
    @PostMapping(value = "/cars")
    public ResponseEntity<Optional<Car>> replaceCar(@RequestBody CarCheckMappingRequest carCheckMappingRequest) {
        throw new NotYetImplementedException();
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        throw new NotYetImplementedException();
    }

    @DeleteMapping(value = "/cars/brand/{brand}")
    public ResponseEntity<Car> deleteCarByBrand(@PathVariable String brand) {
        throw new NotYetImplementedException();
    }

}
