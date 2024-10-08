package de.doubleslash.spring.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringIntroductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntroductionApplication.class, args);
	}



}