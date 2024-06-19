package app.car.cap04.interfaces.incoming.output;

import app.car.cap04.domain.TravelRequestStatus;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class TravelRequestOutput {

    Long id;
    String origin;
    String destination;
    TravelRequestStatus status;
    LocalDateTime creationDate;


}
