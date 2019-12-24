package app.car.cap04.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
