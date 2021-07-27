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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a la clave primaria de la tabla MATRICULA
 * @author Usuario
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "matricula", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cod_estudiante", "cod_periodo"})})

public class Matricula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "cod_matricula", nullable = false)
    private Integer codigo;
 
    /*@Column(name = "cod_estudiante", nullable = false)
    private Integer codigoEstudiante;
    
    @Column(name = "cod_periodo", nullable = false)
    private Integer codigoPeriodo;*/
    
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
 
    @Column(name = "creditos_totales", nullable = false, precision = 8, scale = 2)
    private BigDecimal creditosTotales;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricula")
    @JsonManagedReference
    private List<DetalleMatricula> detalle;
    
    @JoinColumn(name = "cod_estudiante", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    private Estudiante estudiante;
    
    @JoinColumn(name = "cod_periodo", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    private Periodo periodo;

    
}
