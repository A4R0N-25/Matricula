package ec.edu.espe.distribuidas.matricula.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstudianteEditarRS {
    
    @NotNull
    @ApiModelProperty(
            value = "Nombre estudiante",
            example = "Daniel Esteban",
            required = true
    )
    private String nombre;
    
    @NotNull
    @ApiModelProperty(
            value = "Apellido estudiante",
            example = "Yandun Quiroz",
            required = true
    )
    private String apellido;
    
    @NotNull
    @ApiModelProperty(
            value = "Email",
            example = "deyandun2@espe.edu.ec",
            required = true
    )
    private String correo;
    
    @NotNull
    @ApiModelProperty(
            value = "Genero",
            example = "MAS",
            required = true
    )
    private String genero;
    
    @NotNull
    @ApiModelProperty(
            value = "Telefono",
            example = "02365489",
            required = true
    )
    private String telefono;
    
    @NotNull
    @ApiModelProperty(
            value = "Dierccion estudiante",
            example = "VALLE",
            required = true
    )
    private String direccion;
}
