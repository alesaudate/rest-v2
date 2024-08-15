package app.car.cap08.interfaces.incoming.input;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class TravelRequestInput {


    @NotNull(message = "O campo passengerId não pode ser nulo")
    Long passengerId;

    @NotEmpty(message = "O campo origin não pode estar em branco")
    String origin;

    @NotEmpty(message = "O campo destination não pode estar em branco")
    String destination;
}
