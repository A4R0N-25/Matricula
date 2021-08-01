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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a la tabla ESTUDIANTE
 *
 * @author Usuario
 */
@NoArgsConstructor
@Entity
@Data
//@Builder
@Table(name = "estudiante", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tipo_identificacion", "identificacion", "correo"})})
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_estudiante", nullable = false)
    private Integer codigo;

    @Column(name = "tipo_identificacion", nullable = false, length = 3)
    private String tipoIdentificacion;

    @Column(name = "identificacion", nullable = false, length = 15)
    private String identificacion;

    /*@Column(name = "cod_carrera", nullable = false)
    private Integer codigoCarrera;*/
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "contraseña", nullable = false, length = 260)
    private String contraseña;

    @Column(name = "genero", nullable = false, length = 3)
    private String genero;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @JoinColumn(name = "cod_carrera", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private Carrera carrera;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private List<Matricula> matricula;

}
