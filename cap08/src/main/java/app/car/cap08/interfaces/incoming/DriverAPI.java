package app.car.cap08.interfaces.incoming;

import app.car.cap08.domain.Driver;
import app.car.cap08.interfaces.incoming.errorhandling.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Driver API", description = "Manipula dados de motoristas.")
public interface DriverAPI {


    @Operation(description = "Lista todos os motoristas disponíveis")
    List<Driver> listDrivers() ;


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
