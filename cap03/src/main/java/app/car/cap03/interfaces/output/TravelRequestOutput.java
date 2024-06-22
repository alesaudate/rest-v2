package app.car.cap03.interfaces.output;

import app.car.cap03.domain.TravelRequestStatus;
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