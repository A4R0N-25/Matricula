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
import java.util.Date;
import lombok.Data;

/**
 *
 * @author bran-
 */
@Data
public class HorarioRS {
    
    @NotNull
    @ApiModelProperty(
            value = "Dia de la semana",
            example = "LUN",
            required = true
    )
    private String dia;
    
    @NotNull
    @ApiModelProperty(
            value = "Hora inicio asignatura",
            example = "09:30:00",
            required = true
    )
    private Date horaInicio;
    
    @NotNull
    @ApiModelProperty(
            value = "Hora fin asignatura",
            example = "11:30:00",
            required = true
    )
    private Date horaFin;
    
}
