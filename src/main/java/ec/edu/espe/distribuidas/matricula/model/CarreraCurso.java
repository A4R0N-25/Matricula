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

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Representa a la tabla CARRERA_CURSO
 *
 * @author Usuario
 */
@Entity
@Table(name = "carrera_curso")
@Data
public class CarreraCurso implements Serializable {

    @EmbeddedId
    private CarreraCursoPK pk;

    @JoinColumn(name = "cod_curso", referencedColumnName = "cod_curso", nullable = false, insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private Curso curso;

    @JoinColumn(name = "cod_carrera", referencedColumnName = "cod_carrera", nullable = false, insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    private Carrera carrera;

    @Column(name = "estado", nullable = false, length = 3)
    private String estado;

    public CarreraCurso() {
    }

}
