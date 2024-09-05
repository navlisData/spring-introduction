package de.doubleslash.spring.introduction.springcontexttests.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.springcontexttests.setup.SpringInMemoryTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteCarInMemoryTest extends SpringInMemoryTest {

    private final String DELETE_CAR_BY_ROLE_URL = "/employees/role/";
    private final String DELETE_CAR_BY_ID_URL = "/employees/";

    @Test
    public void createAndDeleteCarById_RemovesEntityFromDatabase() {
        Car toDelete = Car.builder().brand("BMW").model("X2").build();

        Car created = this.getRepository().save(toDelete);
        assertThat(created).isNotNull();

        this.getRepository().deleteById(toDelete.getId());

        List<Car> allCars = this.getRepository().findAll();
        assertThat(allCars.contains(toDelete)).isFalse();
    }

}