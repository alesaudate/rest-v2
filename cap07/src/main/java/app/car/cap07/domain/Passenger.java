package app.car.cap07.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Passenger {


    @Id
    @GeneratedValue
    Long id;
    String name;

}
