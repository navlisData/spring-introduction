package de.doubleslash.spring.introduction.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;


@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String model;

    @Setter
    private String brand;

    @CreatedDate @Column(name = "created_at")
    private Instant date;

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
