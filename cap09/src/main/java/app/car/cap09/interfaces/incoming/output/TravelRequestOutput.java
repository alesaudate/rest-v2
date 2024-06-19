package app.car.cap09.interfaces.incoming.output;

import app.car.cap09.domain.TravelRequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TravelRequestOutput {

    Long id;
    String origin;
    String destination;
    TravelRequestStatus status;
    LocalDateTime creationDate;


}
