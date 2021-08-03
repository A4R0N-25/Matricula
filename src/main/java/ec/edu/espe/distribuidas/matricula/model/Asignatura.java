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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * Representa a la tabla CARRERA
 *
 * @author Usuario
 */
@Entity
@Table(name = "asignatura")
@Data
public class Asignatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "cod_asignatura", nullable = false)
    private Integer codigo;    
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;    
  
    @Column(name = "creditos", nullable = false)
    private Short creditos;
    
    @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento", nullable = false, 
            insertable = false, updatable = false)
    @ManyToOne
    private Departamento departamento;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    @JsonManagedReference
    private List<Curso> cursos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<Prerequisito> prerequisitos;

    public Asignatura() {
    }
    
}
