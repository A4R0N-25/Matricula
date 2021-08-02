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
public class MatriculaRQ {

    @NotNull
    @ApiModelProperty(
            value = "Correo del estudiante",
            example = "deyandun2@espe.edu.ec",
            required = true
    )
    private String correo;

    @NotNull
    @ApiModelProperty(
            value = "Codigo de periodo",
            example = "3",
            required = true
    )
    private Integer periodo;

    @NotNull
    @ApiModelProperty(
            value = "Lista codigos de cursos",
            example = "[25, 26, 27, 28]",
            required = true
    )
    private List<Integer> cursos;

    @ApiModelProperty(
            value = "Codigo de matricula",
            example = "22",
            required = true
    )
    private Integer matricula;

}
