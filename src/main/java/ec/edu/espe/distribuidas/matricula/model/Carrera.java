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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a la tabla CARRERA
 *
 * @author Usuario
 */
@Entity
@Table(name = "carrera")
@Data
@NoArgsConstructor
public class Carrera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_carrera", nullable = false)
    private Integer codigo;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "vigencia", nullable = false, length = 3)
    private String vigencia;

    @Column(name = "niveles", nullable = false)
    private Short niveles;

    @Column(name = "modalidad", nullable = false, length = 3)
    private String modalidad;

    @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento", nullable = false,
            insertable = false, updatable = false)
    @ManyToOne
    private Departamento departamento;

}
