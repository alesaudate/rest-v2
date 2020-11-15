package app.car.car09.interfaces.incoming

import app.car.car09.domain.TravelRequest
import app.car.car09.domain.TravelService
import app.car.car09.interfaces.incoming.input.TravelRequestInput
import app.car.car09.interfaces.incoming.mapping.TravelRequestMapper
import app.car.car09.interfaces.incoming.output.TravelRequestOutput
import org.springframework.hateoas.EntityModel
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@Service
@RestController
@RequestMapping(path = ["/travelRequests"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TravelRequestAPI(
        val travelService: TravelService,
        val mapper: TravelRequestMapper
) {

    @PostMapping
    fun makeTravelRequest(@RequestBody travelRequestInput: @Valid TravelRequestInput): EntityModel<TravelRequestOutput> {
        val request = travelService.saveTravelRequest(mapper.map(travelRequestInput))
        val output = mapper.map(request)
        return mapper.buildOutputModel(request, output)
    }

    @GetMapping("/nearby")
    fun listNearbyRequests(@RequestParam currentAddress: String): List<EntityModel<TravelRequestOutput>> {
        val requests: List<TravelRequest> = travelService.listNearbyTravelRequests(currentAddress)
        return mapper.buildOutputModel(requests)
    }
}
