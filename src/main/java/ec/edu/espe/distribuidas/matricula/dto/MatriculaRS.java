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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author bran-
 */
@Data
public class MatriculaRS {
    
    @NotNull
    @ApiModelProperty(
            value = "Codigo estudiante",
            example = "2",
            required = true
    )
    private Integer codigo;
    
    @NotNull
    @ApiModelProperty(
            value = "Fecha matricula",
            example = "2020-11-01",
            required = true
    )
    private Date fecha;
    
    @NotNull
    @ApiModelProperty(
            value = "Periodo matricula",
            example = "PREGRADO S-I MRZ19-JUL19",
            required = true
    )    
    private String periodo;
    
    @NotNull
    @ApiModelProperty(
            value = "Creditos Totales",
            example = "30",
            required = true
    )     
    private BigDecimal creditosTotales;
    
    @NotNull
    @ApiModelProperty(
            value = "Detalle matricula",
            example = "[88, APR, 2021-04-20, 25, 27 ]",
            required = true
    ) 
    private List<MatriculaDetalleRS> detalle;
    
}
