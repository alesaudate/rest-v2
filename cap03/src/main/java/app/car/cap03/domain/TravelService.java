package app.car.cap03.domain;


import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelService {


    @Autowired
    TravelRequestRepository travelRequestRepository;


    public TravelRequest saveTravelRequest(TravelRequest travelRequest) {
        travelRequest.setStatus(TravelRequestStatus.CREATED);
        travelRequest.setCreationDate(LocalDateTime.now());
        return travelRequestRepository.save(travelRequest);
    }






}
