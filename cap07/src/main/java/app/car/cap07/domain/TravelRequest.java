package app.car.cap07.domain;


import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class TravelRequest {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Passenger passenger;
    String origin;
    String destination;

    @Enumerated(EnumType.STRING)
    TravelRequestStatus status;
    Date creationDate;
}
