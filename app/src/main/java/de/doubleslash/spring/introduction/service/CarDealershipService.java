package de.doubleslash.spring.introduction.service;

import de.doubleslash.spring.introduction.controller.CarCheckMappingRequest;
import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarDealershipService {

    private final CarRepository repository;

    public CarDealershipService(CarRepository repository) {
        this.repository = repository;
    }

    public void save(Car car)  {
        repository.save(car);
    }

    public List<Car> findAll()  {
        return repository.findAll();
    }

    public Car findById(Long id) throws CarNotFoundException {
        return repository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public Optional<Car> replaceCar(CarCheckMappingRequest mappingRequest) throws CarNotFoundException {
        //Check for existence of car to replace
        findById(mappingRequest.idOfOldCar());

        //Create new car instance with the id of the old instance, but new field values
        Car replacement = Car.replace(mappingRequest.idOfOldCar(), mappingRequest.replacement());
        repository.save(replacement);
        return Optional.of(replacement);
    }

    public void deleteById(Long id) throws CarNotFoundException {
        repository.findById(id).ifPresentOrElse(car -> repository.deleteById(id), CarNotFoundException::new);
    }

    public int deleteCarByBrand(String brand) {
        return repository.deleteAllByBrand(brand);
    }

}
