package de.doubleslash.spring.introduction.springcontexttests.controller;

import de.doubleslash.spring.introduction.controller.CarCheckMappingRequest;
import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.model.dto.CarCreationDto;
import de.doubleslash.spring.introduction.springcontexttests.setup.SpringInMemoryTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PostCarInMemoryTest extends SpringInMemoryTest {

    private final String POST_CAR_URL = "/cars";

    @Test
    public void replaceCar_ReplacesActualEntityAttributesInDatabase() {
        // Arrange
        Car toReplace = Car.builder().brand("BMW").model("X2").build();
        CarCreationDto creationDto = new CarCreationDto("Fiat", "500");

        // Act
        this.getService().save(toReplace);

        CarCheckMappingRequest cmr = new CarCheckMappingRequest(toReplace.getId(), creationDto);
        this.getService().replaceCar(cmr);
        Car replaced = this.getService().findById(toReplace.getId());

        // Assert
        assertThat(toReplace.getId()).isEqualTo(replaced.getId());
        assertThat(replaced.getBrand()).isEqualTo(creationDto.getBrand());
        assertThat(replaced.getModel()).isEqualTo(creationDto.getModel());
    }

}