package app.car.cap09.interfaces.incoming.output;

import app.car.cap09.domain.TravelRequestStatus;
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
