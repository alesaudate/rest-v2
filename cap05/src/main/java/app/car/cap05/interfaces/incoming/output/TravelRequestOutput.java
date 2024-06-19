package app.car.cap05.interfaces.incoming.output;

import app.car.cap05.domain.TravelRequestStatus;

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
