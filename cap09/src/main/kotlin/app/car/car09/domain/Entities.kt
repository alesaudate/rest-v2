package app.car.car09.domain

import io.swagger.v3.oas.annotations.media.Schema
import java.util.Date
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.Size

@Entity
@Schema(description = "Representa um motorista dentro da plataforma")
data class Driver(
        @Id @GeneratedValue
        val id: Long? = null,

        @Schema(description = "Nome do motorista")
        @Size(min = 5, max = 255)
        val name: String?,

        @Schema(description = "Data de nascimento do motorista")
        val birthDate: Date?
)

@Entity
data class Passenger(
        @Id @GeneratedValue
        val id: Long? = null,
        val name: String?
)

@Entity
data class TravelRequest(
        @Id
        @GeneratedValue
        val id: Long? = null,

        @ManyToOne
        val passenger: Passenger? = null,
        val origin: String,
        val destination: String,

        @Enumerated(EnumType.STRING)
        val status: TravelRequestStatus? = null,
        val creationDate: Date? = null
)

@Entity
data class User(
        @Id @GeneratedValue
        var id: Long? = null,

        @Column(unique = true)
        var username: String? = null,
        var password: String? = null,

        var enabled: Boolean? = null,

        @ElementCollection
        var roles: List<String>? = null

)

enum class TravelRequestStatus {
    CREATED, ACCEPTED, REFUSED
}