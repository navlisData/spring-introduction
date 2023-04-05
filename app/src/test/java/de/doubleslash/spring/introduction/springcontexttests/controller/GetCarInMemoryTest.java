package de.doubleslash.spring.introduction.springcontexttests.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.springcontexttests.setup.SpringInMemoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetCarInMemoryTest extends SpringInMemoryTest {

    private final String GET_CAR_URL = "/cars";



}