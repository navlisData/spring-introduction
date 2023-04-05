package de.doubleslash.spring.introduction;

import de.doubleslash.spring.introduction.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@AllArgsConstructor
@Component
public class CronTask {

    private final CarRepository repository;



}
