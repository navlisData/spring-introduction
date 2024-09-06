package de.doubleslash.spring.introduction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException() {
        super("Car could not be found");
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
