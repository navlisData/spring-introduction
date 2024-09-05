package de.doubleslash.spring.introduction.controller;

import de.doubleslash.spring.introduction.model.Car;

public record CarCheckMappingRequest(Long idOfOldCar, Car replacement)  { }
