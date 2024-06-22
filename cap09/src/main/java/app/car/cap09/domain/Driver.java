package app.car.cap09.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Schema(description = "Representa um motorista dentro da plataforma")
public class Driver {


    @Id
    @GeneratedValue
    Long id;

    @Schema(description = "Nome do motorista")
    @Size(min=5, max = 255)
    String name;

    @Schema(description = "Data de nascimento do motorista")
    LocalDate birthDate;

}
