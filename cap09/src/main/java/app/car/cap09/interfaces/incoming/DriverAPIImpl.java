package app.car.cap09.interfaces.incoming;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import app.car.cap09.domain.Driver;
import app.car.cap09.domain.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RestController()
@RequestMapping(path = "/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverAPIImpl implements DriverAPI {

    private static final int PAGE_SIZE = 10;

    @Autowired
    DriverRepository driverRepository;

    @GetMapping
    public CollectionModel<Driver> listDrivers(@RequestParam("page") int page) {
        Page<Driver> driverPage = driverRepository.findAll(PageRequest.of(page, PAGE_SIZE));
        CollectionModel<Driver> collectionModel = new CollectionModel<>(driverPage.getContent());

        Link lastPageLink = linkTo(methodOn(DriverAPIImpl.class).listDrivers(driverPage.getTotalPages() - 1))
                .withRel("lastPage");

        return collectionModel.add(lastPageLink);
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
