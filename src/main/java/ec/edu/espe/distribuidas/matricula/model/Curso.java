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
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import lombok.ToString;

/**
 * Representa a la tabla CURSO
 *
 * @author Usuario
 */
@Entity
@Table(name = "curso", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nrc"})})
@Data
@NoArgsConstructor
@ToString
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_curso", nullable = false)
    private Integer codigo;

    /*@Column(name = "cod_asignatura", nullable = false)
    private Integer codigoAsignatura;*/
    @Column(name = "nrc", nullable = false)
    private short nrc;

    @Column(name = "cupo", nullable = false)
    private short cupo;

    @Column(name = "disponible", nullable = false)
    private short disponible;

    /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<DetalleMatricula> detalleMatriculas;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    @JsonManagedReference
    private List<CarreraCurso> carreraCursos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    @JsonManagedReference
    private List<Horario> horarios;

    @JoinColumn(name = "cod_asignatura", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Asignatura asignatura;

    @JoinColumn(name = "cod_periodo", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Periodo periodo;

}
