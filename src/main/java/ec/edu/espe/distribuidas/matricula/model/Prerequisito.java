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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Representa a la tabla PRERREQUISITO
 * @author Usuario
 */
@Entity
@Table(name = "prerequisito")
@Data
public class Prerequisito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_prerequisito", nullable = false)
    private Integer codigo;
    
    @Column(name = "cod_asignatura", nullable = false)
    private Integer codigoAsigantura;
    
    @Column(name = "cod_asignatura_pre", nullable = false)
    private Integer codigoAsiganturaPrerrequsito;
 
    @Column(name = "estado", nullable = false, length = 3)
    private String estado;
    
    @JoinColumn(name = "cod_asignatura", referencedColumnName = "cod_asignatura", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private Asignatura asignatura;
    
    @JoinColumn(name = "cod_asignatura_pre", referencedColumnName = "cod_asignatura", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private Asignatura prerequisito;

    public Prerequisito() {
    }
}
