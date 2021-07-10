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
import javax.persistence.UniqueConstraint;

/**
 * Representa a la tabla CURSO
 * @author Usuario
 */
@Entity
@Table(name = "curso", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nrc"})})

public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "cod_curso", nullable = false)
    private Integer codigo;    
 
    @Column(name = "cod_asignatura", nullable = false)
    private Integer codigoAsignatura;
    
    @Column(name = "nrc", nullable = false)
    private short nrc;
 
    @Column(name = "cupo", nullable = false)
    private short cupo;
    
   /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<DetalleMatricula> detalleMatriculas;*/
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Horario> horarios;
    
    @JoinColumn(name = "cod_asignatura", referencedColumnName = "cod_asignatura", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    

    public Curso() {
    }

    public Curso(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(Integer codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }    

    public short getNrc() {
        return nrc;
    }

    public void setNrc(short nrc) {
        this.nrc = nrc;
    }

    public short getCupo() {
        return cupo;
    }

    public void setCupo(short cupo) {
        this.cupo = cupo;
    }

    /*public List<DetalleMatricula> getDetalleMatriculas() {
        return detalleMatriculas;
    }

    public void setDetalleMatriculas(List<DetalleMatricula> detalleMatriculas) {
        this.detalleMatriculas = detalleMatriculas;
    }*/

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }



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
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Curso[ codigo=" + codigo + " ]";
    }
    
}
