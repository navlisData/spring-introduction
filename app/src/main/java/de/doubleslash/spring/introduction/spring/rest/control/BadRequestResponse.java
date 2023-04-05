package de.doubleslash.spring.introduction.spring.rest.control;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BadRequestResponse {
    private List<String> errors;

}
