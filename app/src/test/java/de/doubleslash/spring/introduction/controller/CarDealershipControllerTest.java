package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.repository.CarRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarDealershipControllerTest {

    @Mock
    private CarRepository repository;

    @InjectMocks
    private CarDealershipController controller;








}