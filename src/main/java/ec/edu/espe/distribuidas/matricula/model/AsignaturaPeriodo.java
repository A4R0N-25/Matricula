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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Representa a la tabla CARRERA
 *
 * @author Usuario
 */
@Entity
@Table(name = "asignatura_periodo")
@Data
public class AsignaturaPeriodo implements Serializable {

    @EmbeddedId
    private AsignaturaPeriodoPK pk;
    @Column(name = "estado", length = 3)
    private String estado;
    
    @JoinColumn(name = "cod_asignatura", referencedColumnName = "cod_asignatura", nullable = false, insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    
    @JoinColumn(name = "cod_periodo", referencedColumnName = "cod_periodo", nullable = false, insertable = false, 
            updatable = false)
    @ManyToOne(optional = false)
    private Periodo periodo;

    public AsignaturaPeriodo() {
    }
}
