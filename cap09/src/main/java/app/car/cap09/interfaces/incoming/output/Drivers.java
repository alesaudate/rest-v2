package app.car.cap09.interfaces.incoming.output;

import app.car.cap09.domain.Driver;
import lombok.Getter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import java.util.List;


@Getter
public class Drivers{

    private List<EntityModel<Driver>> drivers;

    private Link[] links;

    public Drivers(List<EntityModel<Driver>> content, Link... links) {
        this.drivers = content;
        this.links = links;
    }
}
