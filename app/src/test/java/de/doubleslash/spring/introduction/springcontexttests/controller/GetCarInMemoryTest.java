package de.doubleslash.spring.introduction.springcontexttests.controller;

import de.doubleslash.spring.introduction.exception.CarNotFoundException;
import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.springcontexttests.setup.SpringInMemoryTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetCarInMemoryTest extends SpringInMemoryTest {

    private final String GET_CAR_URL = "/cars";

    @Test
    public void createAndFindCarById_ReturnsCarWithSameId() {
        // Arrange
        Car expected = Car.builder().brand("BMW").model("X1").build();
        this.getService().save(expected);

        // Act
        Car result = this.getService().findById(expected.getId());

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findNoneExistingCarById_AndThrowsException() {
        // Assert
        assertThrows(CarNotFoundException.class, () -> getService().findById(5L));
    }


}