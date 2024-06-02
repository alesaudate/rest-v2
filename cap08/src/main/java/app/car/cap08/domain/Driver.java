package app.car.cap08.domain;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    Date birthDate;

}
