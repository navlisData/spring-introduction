package de.doubleslash.spring.introduction.springcontexttests.setup;

import de.doubleslash.spring.introduction.repository.CarRepository;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ComponentScan("de.doubleslash.spring.introduction")
@EnableAutoConfiguration
public abstract class SpringInMemoryTest {

    @Autowired
    @Getter
    private CarRepository repository;

    @Autowired
    @Getter
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test started: " + this.getClass().getSimpleName());
        cleanupDb();
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test finished: " + this.getClass().getSimpleName());
    }


    private void cleanupDb(){
        repository.deleteAllInBatch();
    }


    protected HttpHeaders getDefaultHeaders() {
        return new HttpHeaders();
    }

}
