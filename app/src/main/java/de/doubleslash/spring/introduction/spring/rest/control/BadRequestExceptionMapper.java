package de.doubleslash.spring.introduction.spring.rest.control;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BadRequestExceptionMapper {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        // Get the error messages for invalid fields
        final List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "Error at field " + fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        final BadRequestResponse response = BadRequestResponse.builder().errors(errors).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BadRequestResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        final BadRequestResponse response = BadRequestResponse.builder().errors(List.of("Required request body is missing.")).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}