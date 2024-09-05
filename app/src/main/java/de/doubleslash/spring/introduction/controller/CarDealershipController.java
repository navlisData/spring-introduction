package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.service.CarDealershipService;
import de.doubleslash.spring.introduction.service.CarNotFoundException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarDealershipController {

    private final CarDealershipService service;

    public CarDealershipController(CarDealershipService service) {
        this.service = service;
    }

    @Valid
    @NotNull
    @RequestBody
    @PutMapping(value = "/car")
    public ResponseEntity<Car> save(@RequestBody Car car) {
        try {
            service.save(car);
            return ResponseEntity.ok(car);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> all() {
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/car/{id}")
    public ResponseEntity<Car> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (CarNotFoundException cnf) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Valid
    @NotNull
    @RequestBody
    @PostMapping(value = "/cars")
    public ResponseEntity<Optional<Car>> replaceCar(@RequestBody CarCheckMappingRequest carCheckMappingRequest) {
        try {
            Optional<Car> replaceCarOpt = service.replaceCar(carCheckMappingRequest);
            return new ResponseEntity<>(replaceCarOpt, HttpStatus.OK);
        } catch (CarNotFoundException cnf) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/cars/{id}")
    public ResponseEntity<Long> deleteCar(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (CarNotFoundException cnf) {
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/cars/brand/{brand}")
    public ResponseEntity<Integer> deleteCarByBrand(@PathVariable String brand) {
        try {
            return ResponseEntity.ok(service.deleteCarByBrand(brand));
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
