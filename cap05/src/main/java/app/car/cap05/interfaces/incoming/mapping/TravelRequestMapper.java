package app.car.cap05.interfaces.incoming.mapping;

import app.car.cap05.domain.Passenger;
import app.car.cap05.domain.PassengerRepository;
import app.car.cap05.domain.TravelRequest;
import app.car.cap05.interfaces.incoming.PassengerAPI;
import app.car.cap05.interfaces.incoming.input.TravelRequestInput;
import app.car.cap05.interfaces.incoming.output.TravelRequestOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class TravelRequestMapper {

    @Autowired
    private PassengerRepository passengerRepository;

    public TravelRequest map(TravelRequestInput input) {

        Passenger passenger = passengerRepository.findById(input.getPassengerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TravelRequest travelRequest = new TravelRequest();
        travelRequest.setOrigin(input.getOrigin());
        travelRequest.setDestination(input.getDestination());
        travelRequest.setPassenger(passenger);

        return travelRequest;

    }

    public TravelRequestOutput map(TravelRequest travelRequest) {
        TravelRequestOutput travelRequestOutput = new TravelRequestOutput();

        travelRequestOutput.setCreationDate(travelRequest.getCreationDate());
        travelRequestOutput.setDestination(travelRequest.getDestination());
        travelRequestOutput.setId(travelRequest.getId());
        travelRequestOutput.setOrigin(travelRequest.getOrigin());
        travelRequestOutput.setStatus(travelRequest.getStatus());

        return travelRequestOutput;
    }

    public EntityModel<TravelRequestOutput> buildOutputModel(TravelRequest travelRequest, TravelRequestOutput output) {
        EntityModel<TravelRequestOutput> model = EntityModel.of(output);

        Link passengerLink = WebMvcLinkBuilder
                .linkTo(PassengerAPI.class)
                .slash(travelRequest.getPassenger().getId())
                .withRel("passenger")
                .withTitle(travelRequest.getPassenger().getName());
        model.add(passengerLink);
        return model;
    }

    public List<EntityModel<TravelRequestOutput>> buildOutputModel(List<TravelRequest> requests) {
        return requests.stream().map(tr -> buildOutputModel(tr, map(tr))).toList();
    }

}
