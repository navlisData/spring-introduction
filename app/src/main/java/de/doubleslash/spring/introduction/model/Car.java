package de.doubleslash.spring.introduction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;

@Getter
@Entity
@Builder
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Setter
    private String model;

    @Setter
    private String brand;

    @CreatedDate
    private final Instant date;

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
         return String.format("%s[id=%s, model=%s, brand=%s]", this.getClass().getSimpleName(), this.id, this.model, this.brand);
    }
}
