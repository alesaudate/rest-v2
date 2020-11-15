package app.car.car09.interfaces.incoming.mapping

import app.car.car09.domain.Passenger
import app.car.car09.domain.PassengerRepository
import app.car.car09.domain.TravelRequest
import app.car.car09.interfaces.incoming.PassengerAPI
import app.car.car09.interfaces.incoming.input.TravelRequestInput
import app.car.car09.interfaces.incoming.output.TravelRequestOutput
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class TravelRequestMapper(
        val passengerRepository: PassengerRepository
) {

    fun map(input: TravelRequestInput): TravelRequest {
        val passenger: Passenger = passengerRepository.findById(input.passengerId)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        return TravelRequest(
                origin = input.origin,
                destination = input.destination,
                passenger = passenger
        )
    }

    fun map(travelRequest: TravelRequest) =
        TravelRequestOutput(
                creationDate = travelRequest.creationDate!!,
                destination = travelRequest.destination,
                id = travelRequest.id!!,
                origin = travelRequest.origin,
                status = travelRequest.status!!
        )

    fun buildOutputModel(travelRequest: TravelRequest, output: TravelRequestOutput): EntityModel<TravelRequestOutput> {
        val model= EntityModel<TravelRequestOutput>(output)
        travelRequest.passenger?.apply {
            val link = WebMvcLinkBuilder
                    .linkTo(PassengerAPI::class.java)
                    .slash(this.id)
                    .withRel("passenger")
                    .withTitle(this.name!!)
            model.add(link)
        }

        return model
    }

    fun buildOutputModel(requests: List<TravelRequest>) =
        requests.map { tr: TravelRequest -> buildOutputModel(tr, map(tr)) }

}