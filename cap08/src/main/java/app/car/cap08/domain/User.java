package app.car.cap08.domain;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
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
