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

/**
 * Representa a la tabla ASIGNATURA
 * @author Usuario
 */
@Entity
@Table(name = "asignatura")

public class Asignatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "cod_asignatura", nullable = false)
    private Integer codigo;    
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;    
  
    @Column(name = "creditos", nullable = false)
    private short creditos;
    
    @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Departamento departamento;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<Prerequisito> prerequisitos;

    public Asignatura() {
    }

    public Asignatura(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public List<Prerequisito> getPrerequisitos() {
        return prerequisitos;
    }

    public void setPrerequisitos(List<Prerequisito> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCreditos() {
        return creditos;
    }

    public void setCreditos(short creditos) {
        this.creditos = creditos;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    
    /*public List<Prerequisito> getPrerequisitos() {
        return prerequisitos;
    }

    public void setPrerequisitos(List<Prerequisito> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asignatura other = (Asignatura) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Asignatura[ codigo=" + codigo + " ]";
    }
    
}
