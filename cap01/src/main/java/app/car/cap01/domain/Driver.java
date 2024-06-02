package app.car.cap01.domain;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Driver {


    @Id
    Long id;
    String name;
    Date birthDate;

}
