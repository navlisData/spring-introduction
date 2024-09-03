package de.doubleslash.spring.introduction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Car {

    @Id @GeneratedValue
    private Long id;
    private String model;
    private String brand;

    @CreatedDate
    private Instant date;

    private Car(long id, String model, String brand, Instant date) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.date = date;
    }

    public static Car create(long id, String model, String brand, Instant date) {
        return new Car(id, model, brand, date);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Car otherCar)) {
            return false;
        }
        return Objects.equals(otherCar.hashCode(), this.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, brand);
    }

    @Override
    public String toString() {
         return String.format("Car[id=%s, model=%s, brand=%s]", this.id, this.model, this.brand);
    }
}
