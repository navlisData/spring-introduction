package de.doubleslash.spring.introduction.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarNotFoundException extends Exception {
    private final String msg = "Car could not be found";
}
