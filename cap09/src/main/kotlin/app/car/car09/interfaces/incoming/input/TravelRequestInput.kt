package app.car.car09.interfaces.incoming.input

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class TravelRequestInput(
        @NotNull(message = "O campo passengerId não pode ser nulo")
        val passengerId: Long,

        @NotEmpty(message = "O campo origin não pode estar em branco")
        val origin:  String,

        @NotEmpty(message = "O campo destination não pode estar em branco")
        var destination: String

)