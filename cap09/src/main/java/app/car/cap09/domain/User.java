package app.car.cap09.domain;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column(unique = true)
    String username;
    String password;

    Boolean enabled;

    @ElementCollection
    List<String> roles;

}
