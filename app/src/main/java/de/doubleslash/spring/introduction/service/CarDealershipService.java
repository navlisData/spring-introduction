package de.doubleslash.spring.introduction.service;

import de.doubleslash.spring.introduction.controller.CarCheckMappingRequest;
import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.NotImplementedException;
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

    public Car findById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Optional<Car> replaceCar(CarCheckMappingRequest carCheckMappingRequest) {
        //TODO: Check existence of carToReplace -> Exception handling
        throw new NotImplementedException();
    }

    public void deleteById(Long id) throws EntityNotFoundException {
        repository.findById(id).ifPresentOrElse(car -> repository.deleteById(id), EntityNotFoundException::new);
    }

    public int deleteCarByBrand(String brand) {
        return repository.deleteAllByBrand(brand);
    }

}
