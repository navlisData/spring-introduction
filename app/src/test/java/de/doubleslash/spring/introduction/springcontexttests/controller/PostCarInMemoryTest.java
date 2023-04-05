package de.doubleslash.spring.introduction.springcontexttests.controller;

import de.doubleslash.spring.introduction.model.Car;
import de.doubleslash.spring.introduction.springcontexttests.setup.SpringInMemoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class PostCarInMemoryTest extends SpringInMemoryTest {

    private final String POST_CAR_URL = "/cars";


}