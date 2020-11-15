package app.car.car09.domain

import app.car.car09.interfaces.outcoming.GMapsService
import org.springframework.stereotype.Service
import java.util.Date
import java.util.function.Predicate

@Service
class TravelService(
        val travelRequestRepository: TravelRequestRepository,
        val gMapsService: GMapsService
) {

    fun saveTravelRequest(travelRequest: TravelRequest) =
            travelRequestRepository.save(
                    travelRequest.copy(
                            status = TravelRequestStatus.CREATED,
                            creationDate = Date()))



    fun listNearbyTravelRequests(currentAddress: String) = travelRequestRepository.findByStatus(TravelRequestStatus.CREATED)
            .filter {
                gMapsService.getDistanceBetweenAddresses(currentAddress, it.origin)?.let { result ->
                    result < MAX_TRAVEL_TIME
                } ?: false
            }


    companion object {
        const val MAX_TRAVEL_TIME: Int = 600;
    }
}