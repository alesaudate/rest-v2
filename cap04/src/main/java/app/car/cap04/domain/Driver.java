package app.car.cap04.domain;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Driver {


    @Id
    @GeneratedValue
    Long id;
    String name;
    Date birthDate;

}
