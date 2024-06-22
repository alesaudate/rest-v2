package app.car.cap01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Driver {


    @Id
    Long id;
    String name;
    LocalDate birthDate;

}
