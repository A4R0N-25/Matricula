/*
 * Copyright (c) 2021 bran-.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    bran- - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.matricula.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author bran-
 */
@Data
@Builder
public class EstudianteRQ {

    @NotNull
    @ApiModelProperty(
            value = "Tipo identificacion",
            example = "CED",
            required = true
    )
    private String tipo;
    
    @NotNull
    @ApiModelProperty(
            value = "Identificacion",
            example = "1714990965",
            required = true
    )
    private String identificacion;
    
    @NotNull
    @ApiModelProperty(
            value = "Nombre",
            example = "Brandon Aaron",
            required = true
    )
    private String nombre;
    
    @NotNull
    @ApiModelProperty(
            value = "Apellido",
            example = "Romero Cruz",
            required = true
    )
    private String apellido;
    
    @NotNull
    @ApiModelProperty(
            value = "Correo",
            example = "baromero@espe.edu.ec",
            required = true
    )
    private String correo;
    
    @NotNull
    @ApiModelProperty(
            value = "Password",
            example = "123",
            required = true
    )
    private String contrasena;
    
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
            example = "0223336548",
            required = true
    )
    private String telefono;
    
    @NotNull
    @ApiModelProperty(
            value = "Direccion",
            example = "QUITO",
            required = true
    )
    private String direccion;
    
    @NotNull
    @ApiModelProperty(
            value = "Carrera",
            example = "Tecnologias de la Informacion",
            required = true
    )
    private Integer carrera;
    
}
