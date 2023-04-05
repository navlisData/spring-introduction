package de.doubleslash.spring.introduction.exception;

public class CarNotFoundException extends RuntimeException{

    CarNotFoundException(final Long id){
        super("Could not find car " + id);
    }

}
