package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.service.CarDealershipService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarDealershipControllerTest {

    @Mock
    private CarDealershipService service;

    private CarDealershipController controller;

    private List<Car> dummyList;

    @BeforeEach
    public void setUp() {
        dummyList = List.of(Car.builder().model("X1").brand("BMW").build(), Car.builder().model("Fiesta").brand("Ford").build());

        controller = new CarDealershipController(service);
    }

    @Test
    public void findAll_ReturnsResponseWithBodyAndStatusOK() {
        when(service.findAll()).thenReturn(dummyList);

        //Testing findAll API endpoint
        final ResponseEntity<List<Car>> result = controller.all();
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(dummyList);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).findAll();
    }

    @Test
    public void findById_ReturnsEntityAndResponseWithBodyAndStatusOK() {
        Car expected = dummyList.get(0);
        when(service.findById(anyLong())).thenReturn(expected);

        //Testing findById API endpoint
        final ResponseEntity<Car> result = controller.get(0L);
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(expected);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).findById(anyLong());
    }

    @Test
    public void findById_ThrowsExceptionAndStatusNotFound() {
        Long id = 999L;
        when(service.findById(id)).thenThrow(new EntityNotFoundException());

        //Testing findById API endpoint
        final ResponseEntity<Car> result = controller.get(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(service).findById(id);
    }

    @Test
    public void deleteCarByBrand_MinimizesSizeOfCarEntriesAndStatusOK() {
        int expected = 0;
        when(service.deleteCarByBrand(anyString())).thenReturn(expected);

        //Testing deleteCarByBrand API endpoint
        final ResponseEntity<Integer> result = controller.deleteCarByBrand(anyString());
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(expected);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).deleteCarByBrand(anyString());
    }

    @Test
    public void deleteCarById_ReturnsEntityAndResponseWithBodyAndStatusOK() {
        doNothing().when(service).deleteById(anyLong());

        //Testing deleteCarByBrand API endpoint
        final ResponseEntity<Long> result = controller.deleteCar(anyLong());
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).deleteById(anyLong());
    }

    @Test
    public void deleteCarById_ThrowsExceptionAndStatusNotFound() {
        Long id = 999L;
        doThrow(new EntityNotFoundException()).when(service).deleteById(id);

        //Testing deleteCarById API endpoint
        final ResponseEntity<Long> result = controller.deleteCar(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(service).deleteById(id);
    }
}