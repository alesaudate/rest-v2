package app.car.cap06.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Driver {


    @Id
    @GeneratedValue
    Long id;
    String name;
    LocalDate birthDate;

}
