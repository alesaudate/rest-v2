package app.car.cap09.interfaces.incoming.output;

import app.car.cap09.domain.Driver;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;

import java.util.Collection;
import java.util.stream.Collectors;


@NoArgsConstructor
public class Drivers extends CollectionModel<EntityModel<Driver>> {


    public Drivers(Iterable<EntityModel<Driver>> content, Link... links) {
        super(content, links);
    }

    @Override
    @JsonUnwrapped
    public Collection<EntityModel<Driver>> getContent() {
        return super.getContent();
    }


    @Override
    @JsonProperty("links")
    public Links getLinks() {
        return super.getLinks();
    }
}
