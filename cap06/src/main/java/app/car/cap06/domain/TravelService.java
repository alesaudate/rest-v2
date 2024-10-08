package app.car.cap06.domain;


import app.car.cap06.interfaces.outcoming.GMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TravelService {


    @Autowired
    TravelRequestRepository travelRequestRepository;

    @Autowired
    GMapsService gMapsService;

    private static final int MAX_TRAVEL_TIME = 600;

    public TravelRequest saveTravelRequest(TravelRequest travelRequest) {
        travelRequest.setStatus(TravelRequestStatus.CREATED);
        travelRequest.setCreationDate(LocalDateTime.now());
        return travelRequestRepository.save(travelRequest);
    }


    public List<TravelRequest> listNearbyTravelRequests(String currentAddress) {
        List<TravelRequest> requests = travelRequestRepository.findByStatus(TravelRequestStatus.CREATED);
        return requests
                .stream()
                .filter(tr -> gMapsService.getDistanceBetweenAddresses(currentAddress, tr.getOrigin()) < MAX_TRAVEL_TIME)
                .toList();
    }
}
