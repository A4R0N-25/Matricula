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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * Representa a la tabla HORARIO
 *
 * @author Usuario
 */
@Entity
@Table(name = "horario")
@Data
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_horario", nullable = false)
    private Integer codigo;

    @Column(name = "cod_curso", nullable = false)
    private Integer codigoCurso;

    @Column(name = "dia", nullable = false, length = 3)
    private String dia;

    @Column(name = "hora_inicio", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Column(name = "hora_fin", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaFin;

    @JoinColumn(name = "cod_curso", referencedColumnName = "cod_curso", nullable = false, insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private Curso curso;

    public Horario() {
    }

}
