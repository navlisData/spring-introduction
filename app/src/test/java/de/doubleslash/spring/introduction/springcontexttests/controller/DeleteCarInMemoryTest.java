package de.doubleslash.spring.introduction.springcontexttests.controller;

import de.doubleslash.spring.introduction.exception.CarNotFoundException;
import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.springcontexttests.setup.SpringInMemoryTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteCarInMemoryTest extends SpringInMemoryTest {

    private final String DELETE_CAR_BY_ROLE_URL = "/employees/role/";
    private final String DELETE_CAR_BY_ID_URL = "/employees/";

    @Test
    public void createAndDeleteCarById_RemovesEntityFromDatabase() {
        // Arrange
        Car toDelete = Car.builder().brand("BMW").model("X2").build();

        // Act
        this.getService().save(toDelete);
        this.getService().deleteById(toDelete.getId());
        List<Car> allCars = this.getRepository().findAll();

        // Assert
        assertThat(allCars.contains(toDelete)).isFalse();
    }


    @Test
    public void deleteNoneExistingCarById_ThrowsException() {
        // Assert
        assertThrows(CarNotFoundException.class, () -> getService().deleteById(5L));
    }
}