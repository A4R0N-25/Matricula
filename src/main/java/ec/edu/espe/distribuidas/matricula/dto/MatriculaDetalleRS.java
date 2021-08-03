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
public class MatriculaDetalleRS {
    
    @NotNull
    @ApiModelProperty(
            value = "Codigo matricula",
            example = "22",
            required = true
    )
    private Integer codigo;
    
    @NotNull
    @ApiModelProperty(
            value = "NRC asignatura",
            example = "4000",
            required = true
    )
    private Short nrc;
    
    @NotNull
    @ApiModelProperty(
            value = "Nombre asignatura",
            example = "Ecuaciones Diferenciales",
            required = true
    )
    private String materia;
    
    @NotNull
    @ApiModelProperty(
            value = "Cantidad de creditos",
            example = "24",
            required = true
    )
    private Short creditos;
    
    @NotNull
    @ApiModelProperty(
            value = "Estado materia",
            example = "REP",
            required = true
    )
    private String estado;
    
}
