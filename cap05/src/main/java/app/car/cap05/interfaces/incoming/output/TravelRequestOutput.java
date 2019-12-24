package app.car.cap05.interfaces.incoming.output;

import app.car.cap05.domain.TravelRequestStatus;
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
