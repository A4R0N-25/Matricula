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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa a la tabla PERIODO.
 * @author Usuario
 */
@Entity
@Table(name = "periodo")

public class Periodo implements Serializable {

    private static final long serialVersionUID = 1234567L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "cod_periodo", nullable = false)
    private Integer codigo;      
  
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;    
    
    @Column(name = "inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inicio;    
   
    @Column(name = "fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fin;
    
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "periodo")
    private List<AsignaturaPeriodo> asignaturaPeriodo;*/

    public Periodo() {
    }

    public Periodo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    /*public List<AsignaturaPeriodo> getAsignaturaPeriodo() {
        return asignaturaPeriodo;
    }

    public void setAsignaturaPeriodo(List<AsignaturaPeriodo> asignaturaPeriodo) {
        this.asignaturaPeriodo = asignaturaPeriodo;
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
        final Periodo other = (Periodo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Periodo[ codigo=" + codigo + " ]";
    }
    
}
