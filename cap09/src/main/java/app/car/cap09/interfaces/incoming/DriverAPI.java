package app.car.cap09.interfaces.incoming;

import app.car.cap09.domain.Driver;
import app.car.cap09.interfaces.incoming.errorhandling.ErrorResponse;
import app.car.cap09.interfaces.incoming.output.Drivers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

@Tag(name = "Driver API", description = "Manipula dados de motoristas.")
public interface DriverAPI {


    @Operation(description = "Lista todos os motoristas disponíveis")
    Drivers listDrivers(int page);


    @Operation(
            description = "Localiza um motorista específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o motorista tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404",
                            description = "Caso o motorista não tenha sido encontrado",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            })
    Driver findDriver(
            @Parameter(description = "ID do motorista a ser localizado") Long id) ;

    Driver createDriver(
            @Parameter(description = "Dados do motorista a ser criado") Driver driver) ;

    Driver fullUpdateDriver(Long id, Driver driver);

    Driver incrementalUpdateDriver(Long id, Driver driver) ;

    void deleteDriver(Long id) ;


}
