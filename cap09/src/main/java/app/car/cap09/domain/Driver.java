package app.car.cap09.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
