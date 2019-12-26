package app.car.cap03.interfaces.output;

import app.car.cap03.domain.TravelRequestStatus;
import java.util.Date;
import lombok.Data;

@Data
public class TravelRequestOutput {

    Long id;
    String origin;
    String destination;
    TravelRequestStatus status;
    Date creationDate;
}