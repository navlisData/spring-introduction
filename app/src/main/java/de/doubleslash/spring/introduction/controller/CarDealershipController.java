package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.model.dto.CarCreationDto;
import de.doubleslash.spring.introduction.service.CarDealershipService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@CrossOrigin(origins = "*")
public class CarDealershipController {

    private final CarDealershipService service;

    public CarDealershipController(CarDealershipService service) {
        this.service = service;
    }

    @PutMapping(value = "/car")
    public ResponseEntity<Car> save(@Valid @RequestBody CarCreationDto carCreationDto) {
        Car carToSave = carCreationDto.toCar();
        service.save(carToSave);
        return ResponseEntity.ok(carToSave);
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> all() {
        System.out.println("request");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/car/{id}")
    public ResponseEntity<Car> get(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(value = "/cars")
    public ResponseEntity<Optional<Car>> replaceCar(@Valid @RequestBody CarCheckMappingRequest carCheckMappingRequest) {
        Optional<Car> replaceCarOpt = service.replaceCar(carCheckMappingRequest);
        return new ResponseEntity<>(replaceCarOpt, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<Long> deleteCar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping(value = "/cars/brand/{brand}")
    public ResponseEntity<Integer> deleteCarByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(service.deleteCarByBrand(brand));
    }

}
