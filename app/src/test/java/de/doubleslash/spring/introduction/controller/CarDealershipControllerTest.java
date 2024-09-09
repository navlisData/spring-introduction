package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.exception.CarNotFoundException;
import de.doubleslash.spring.introduction.exception.GlobalExceptionHandler;
import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.service.CarDealershipService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarDealershipControllerTest {

    @Mock
    private CarDealershipService service;

    private CarDealershipController controller;

    private List<Car> dummyList;

    @BeforeEach
    public void setUp() {
        System.out.println("Test started: " + this.getClass().getSimpleName());
        dummyList = List.of(Car.builder().model("X1").brand("BMW").build(), Car.builder().model("Fiesta").brand("Ford").build());

        controller = new CarDealershipController(service);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test finished: " + this.getClass().getSimpleName());
    }

    @Test
    public void findAll_ReturnsResponseWithBodyAndStatusOK() {
        // Arrange
        when(service.findAll()).thenReturn(dummyList);

        // Act
        final ResponseEntity<List<Car>> result = controller.all();

        // Assert
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(dummyList);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).findAll();
    }

    @Test
    public void findById_ReturnsEntityAndResponseWithBodyAndStatusOK() {
        // Arrange
        Car expected = dummyList.get(0);
        when(service.findById(anyLong())).thenReturn(expected);

        // Act
        final ResponseEntity<Car> result = controller.get(0L);

        // Assert
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(expected);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).findById(anyLong());
    }

    @Test
    public void replaceCar_ReturnsExpectedOptional() {
        // Arrange
        Optional<Car> expected = Optional.of(Car.builder().brand("BMW").model("X1").build());
        when(service.replaceCar(any())).thenReturn(expected);

        // Act
        final ResponseEntity<Optional<Car>> result = controller.replaceCar(any());

        // Assert
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(expected);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).replaceCar(any());
    }


//    @Test
//    public void replaceCar_ThrowsExceptionWithInvalidRequestBody() throws Exception {
//        when(service.replaceCar(any())).thenReturn(null);
//
//        CarCheckMappingRequest mappingRequest = new CarCheckMappingRequest(0L, new CarReplacementDto("", "X1"));
//        String json =  new ObjectMapper().writeValueAsString(mappingRequest);
//
//        //Testing replaceCar API endpoint with exception
//        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
//        mvc.perform(post("/cars")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json)
//        ).andExpect(status().isBadRequest());
//        verify(service).replaceCar(any());
//    }

    @Test
    public void findById_ThrowsExceptionAndStatusNotFound() throws Exception {
        // Arrange
        when(service.findById(anyLong())).thenThrow(new CarNotFoundException());

        // Act
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();

        // Assert
        mvc.perform(get("/car/999")).andExpect(status().isNotFound());
    }

    @Test
    public void deleteCarByBrand_ReturnsSizeOfCarEntriesAndStatusOK() {
        // Arrange
        int expected = 0;
        when(service.deleteCarByBrand(anyString())).thenReturn(expected);

        // Act
        final ResponseEntity<Integer> result = controller.deleteCarByBrand(anyString());

        // Assert
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(expected);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).deleteCarByBrand(anyString());
    }

    @Test
    public void deleteCarById_ReturnsEntityAndResponseWithBodyAndStatusOK() {
        // Arrange
        doNothing().when(service).deleteById(anyLong());

        // Act
        final ResponseEntity<Long> result = controller.deleteCar(anyLong());

        // Assert
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(service).deleteById(anyLong());
    }

    @Test
    public void deleteCarById_ThrowsExceptionAndStatusNotFound() throws Exception {
        // Arrange
        doThrow(new CarNotFoundException()).when(service).deleteById(anyLong());

        // Act
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();

        // Assert
        mvc.perform(delete("/cars/999")).andExpect(status().isNotFound());
        verify(service).deleteById(anyLong());
    }
}