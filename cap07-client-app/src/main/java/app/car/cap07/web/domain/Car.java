package app.car.cap07.web.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;



@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Car {


    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Model model;
    BigDecimal price;

    String thumbnail;


}
