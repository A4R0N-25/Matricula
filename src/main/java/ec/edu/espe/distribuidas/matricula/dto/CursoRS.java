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
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author bran-
 */
@Data
@Builder
public class CursoRS {
    
    @NotNull
    @ApiModelProperty(
            value = "Codigo curso",
            example = "15",
            required = true
    )
    private Integer codigo;
    
    @NotNull
    @ApiModelProperty(
            value = "NRC curso",
            example = "4000",
            required = true
    )
    private Short nrc;
    
    @NotNull
    @ApiModelProperty(
            value = "Cupo curso",
            example = "20",
            required = true
    )
    private Short cupo;
    
    @NotNull
    @ApiModelProperty(
            value = "Nombre asignatura",
            example = "Fisica I",
            required = true
    )
    private String asignatura;
    
    @NotNull
    @ApiModelProperty(
            value = "Cupos disponibles",
            example = "12",
            required = true
    )
    private Short disponible;
    
    @NotNull
    @ApiModelProperty(
            value = "Cantidad de creditos",
            example = "4",
            required = true
    )
    private Short creditos;
    
    @NotNull
    @ApiModelProperty(
            value = "Carreras",
            example = "[Mecatronica, Tecnologias de la Informacion]",
            required = true
    )
    private List<String> carreras;
    
    @NotNull
    @ApiModelProperty(
            value = "Horarios de clase",
            example = "[LUN, 07:15:00 , 09:15:00]",
            required = true
    )
    private List<HorarioRS> horarios;

}
