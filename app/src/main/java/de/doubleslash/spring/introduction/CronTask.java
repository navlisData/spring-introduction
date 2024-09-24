package de.doubleslash.spring.introduction;

import de.doubleslash.spring.introduction.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class CronTask {

    @Autowired
    private final CarRepository repository;

    /**
     * CronJob: Every 5 minutes
     *                 ┌───────────── Seconds (0-59)
     *                 │  ┌───────────── Minutes (0-59) (here: every 5 minutes)
     *                 │  │  ┌─────────────── Hour (0-23)
     *                 │  │  │ ┌───────────────── Day of month (1-31)
     *                 │  │  │ │ ┌────────────────── Month (1-12 or Jan-Dec)
     *                 │  │  │ │ │ ┌──────────────────── Day of the Week (0-7, both 0 and 7 represent Sunday)
     */
    @Scheduled(cron = "* */5 * * * *")
    public void removeExpiredCars() {
//        repository.deleteAllByDateBefore(Instant.now().minus(24, ChronoUnit.HOURS));
    }

}
