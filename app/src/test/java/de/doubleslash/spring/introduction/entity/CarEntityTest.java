package de.doubleslash.spring.introduction.entity;

import de.doubleslash.spring.introduction.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarEntityTest {

    private Car bmwCarExample;

    @BeforeEach
    public void setUp() {
        bmwCarExample = Car.builder().brand("BMW").model("X1").build();
    }

    @Test
    public void isEqual_ReturnsFalseForOtherEntityAttributes() {
        // Arrange
        Car c2 = Car.replace(bmwCarExample.getId(), Car.builder().brand("Fiat").model("500").build());

        // Assert
        assertThat(bmwCarExample.equals(c2)).isEqualTo(false);
    }

    @Test
    public void isEqual_ReturnsFalseForOtherEntityClass() {
        // Arrange
        Boolean otherEntity = Boolean.FALSE;

        // Assert
        assertThat(bmwCarExample.equals(otherEntity)).isEqualTo(false);
    }

    @Test
    public void toString_ContainsEntityAttributes() {
        // Arrange
        Car car = Car.builder().brand("BMW").model("X1").build();

        // Assert
        assertThat(car.toString()).contains("BMW");
        assertThat(car.toString()).contains("X1");
        assertThat(car.toString()).contains(car.getClass().getSimpleName());
    }

    @Test
    public void replaceCar_ReturnsNewCarWithNewAttributes() {
        // Arrange
        Long oldEntityId = 5L;

        // Act
        Car replaced = Car.replace(oldEntityId, bmwCarExample);

        // Assert
        assertThat(replaced.getId()).isEqualTo(oldEntityId);
        assertThat(replaced.getModel()).endsWith(bmwCarExample.getModel());
        assertThat(replaced.getBrand()).endsWith(bmwCarExample.getBrand());
    }
}
