package app.car.cap09.interfaces.incoming;

import app.car.cap09.domain.Driver;
import app.car.cap09.domain.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RestController()
@RequestMapping(path = "/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverAPIImpl implements DriverAPI {

    private static final int PAGE_SIZE = 10;

    @Autowired
    DriverRepository driverRepository;

    @GetMapping
    public CollectionModel<EntityModel<Driver>> listDrivers(@RequestParam("page") int page) {
        Page<Driver> driverPage = driverRepository.findAll(PageRequest.of(page, PAGE_SIZE));

        List<EntityModel<Driver>> driverList = new ArrayList<>();
        for (Driver driver : driverPage.getContent()) {
            EntityModel<Driver> objectEntityModel = new EntityModel(driver);
            driverList.add(objectEntityModel);
        }

        Link lastPageLink = linkTo(methodOn(DriverAPIImpl.class).listDrivers(driverPage.getTotalPages() - 1))
                .withRel("lastPage");

        return new CollectionModel<>(driverList, lastPageLink);
    }

    @GetMapping("/{id}")
    public Driver findDriver(
            @PathVariable("id") Long id) {
        return driverRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Driver createDriver(
            @RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    @PutMapping("/{id}")
    public Driver fullUpdateDriver(@PathVariable("id") Long id, @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);
        foundDriver.setBirthDate(driver.getBirthDate());
        foundDriver.setName(driver.getName());
        return driverRepository.save(foundDriver);
    }

    @PatchMapping("/{id}")
    public Driver incrementalUpdateDriver(@PathVariable("id") Long id, @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);
        foundDriver.setBirthDate(Optional.ofNullable(driver.getBirthDate()).orElse(foundDriver.getBirthDate()));
        foundDriver.setName(Optional.ofNullable(driver.getName()).orElse(foundDriver.getName()));
        return driverRepository.save(foundDriver);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable("id") Long id) {
        driverRepository.delete(findDriver(id));
    }
}
