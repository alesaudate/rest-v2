package app.car.cap05.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Passenger {


    @Id
    @GeneratedValue
    Long id;
    String name;

}
