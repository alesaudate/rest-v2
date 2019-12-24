package app.car.cap01.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Driver {


    @Id
    Long id;
    String name;
    Date birthDate;

}
