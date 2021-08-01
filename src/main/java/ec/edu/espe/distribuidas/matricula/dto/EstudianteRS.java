package ec.edu.espe.distribuidas.matricula.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstudianteRS {
    
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contraseña;
    private String genero;
    private String telefono;
    private String direccion;
    private Integer carrera;

}
