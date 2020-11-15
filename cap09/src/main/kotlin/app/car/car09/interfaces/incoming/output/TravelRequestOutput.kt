package app.car.car09.interfaces.incoming.output

import app.car.car09.domain.TravelRequestStatus
import java.util.Date

open class TravelRequestOutput(
        val id: Long,
        var origin: String,
        var destination: String,
        var status: TravelRequestStatus,
        var creationDate: Date
)