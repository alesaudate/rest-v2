package app.car.car09.interfaces.incoming.errorhandling

data class ErrorResponse(
        val errors: List<ErrorData>
)

data class ErrorData(
        val message: String
)