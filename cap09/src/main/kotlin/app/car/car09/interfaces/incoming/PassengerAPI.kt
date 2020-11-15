package app.car.car09.interfaces.incoming

import app.car.car09.domain.Passenger
import app.car.car09.domain.PassengerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.Optional
import javax.annotation.security.RolesAllowed

@Service
@RestController
@RequestMapping(path = ["/passengers"], produces = [MediaType.APPLICATION_JSON_VALUE])
open class PassengerAPI(
        val passengerRepository: PassengerRepository
) {


    @GetMapping
    fun listPassengers() = passengerRepository.findAll()

    @GetMapping("/{id}")
    fun findPassenger(@PathVariable("id") id: Long) = passengerRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    fun createPassenger(@RequestBody passenger: Passenger) = passengerRepository.save(passenger)

    @PutMapping("/{id}")
    fun fullUpdatePassenger(@PathVariable("id") id: Long, @RequestBody passenger: Passenger) =
        passengerRepository.save(findPassenger(id).let {
            it.copy(name = passenger.name)
        })

    @PatchMapping("/{id}")
    fun incrementalUpdatePassenger(@PathVariable("id") id: Long, @RequestBody passenger: Passenger) =
       passengerRepository.save(findPassenger(id).let {
           it.copy(name = passenger.name ?: it.name)
       })


    @DeleteMapping("/{id}")
    fun deletePassenger(@PathVariable("id") id: Long)= passengerRepository.delete(findPassenger(id))

}