package de.doubleslash.spring.introduction.model;

import de.doubleslash.spring.introduction.spring.configuration.entity.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;

@Entity
@Builder
public class Car extends Auditable {


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Car))
            return false;
        Car car = (Car) o;
        return Objects.equals(this.id, car.id) && Objects.equals(this.model, car.model)
                && Objects.equals(this.brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.model, this.brand);
    }

    @Override
    public String toString() {
        return "Car {" + "id=" + this.id + ", model='" + this.model + '\'' + ", brand='" + this.brand + '\'' + '}';
    }
}
