package de.doubleslash.spring.introduction;

import de.doubleslash.spring.introduction.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CronTask {

    private final CarRepository repository;



}
