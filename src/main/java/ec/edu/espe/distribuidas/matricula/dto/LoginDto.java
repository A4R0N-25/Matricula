package ec.edu.espe.distribuidas.matricula.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {

    @NotNull
    @ApiModelProperty(
            value = "Nombre carrera",
            example = "Tecnologias de la Informacion",
            required = true
    )
    private String correo;
    
    @NotNull
    @ApiModelProperty(
            value = "Password",
            example = "123",
            required = true
    )
    private String password;
}
