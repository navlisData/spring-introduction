package de.doubleslash.spring.introduction.springcontexttests.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.springcontexttests.setup.SpringInMemoryTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GetCarInMemoryTest extends SpringInMemoryTest {

    private final String GET_CAR_URL = "/cars";


    @Test
    public void createAndFindCarById_ReturnsCarWithSameId() {
        Car expected = Car.builder().brand("BMW").model("X1").build();

        this.getRepository().save(expected);
        Optional<Car> result = this.getRepository().findById(expected.getId());

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(expected.getId());
    }


    @Test
    public void findNoneExistingCarById_AndThrowsException() {
        Car expected = Car.builder().brand("BMW").model("X1").build();

        this.getRepository().save(expected);
        Optional<Car> result = this.getRepository().findById(expected.getId());

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(expected.getId());
    }

}