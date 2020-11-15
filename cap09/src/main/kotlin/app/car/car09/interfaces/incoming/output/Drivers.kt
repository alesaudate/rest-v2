package app.car.car09.interfaces.incoming.output

import app.car.car09.domain.Driver
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link

open class Drivers(
        val drivers: List<EntityModel<Driver>>,
        val links: List<Link>
)