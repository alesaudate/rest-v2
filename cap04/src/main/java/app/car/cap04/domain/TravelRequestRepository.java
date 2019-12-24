package app.car.cap04.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRequestRepository extends JpaRepository<TravelRequest, Long> {

    List<TravelRequest> findByStatus(TravelRequestStatus status);
}
