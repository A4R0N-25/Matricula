/*
 * Copyright (c) 2021 Usuario.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Usuario - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.matricula.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

/**
 * Representa a la clave primaria de la tabla ASIGNATURA_PERIODO
 *
 * @author Usuario
 */
@Embeddable
@Data
public class AsignaturaPeriodoPK implements Serializable {

    @Column(name = "cod_periodo", nullable = false)
    private Integer codigoPeriodo;

    @Column(name = "cod_asignatura", nullable = false)
    private Integer codigoAsignatura;

    public AsignaturaPeriodoPK() {
    }

}
