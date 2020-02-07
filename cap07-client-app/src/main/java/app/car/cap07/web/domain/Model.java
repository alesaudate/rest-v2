package app.car.cap07.web.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    @Id
    @GeneratedValue
    Long id;
    String modelName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Manufacturer manufacturer;

    String details;
    Integer year;


}
