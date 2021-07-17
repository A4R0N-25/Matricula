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

/**
 * Representa a la tabla PRERREQUISITO
 * @author Usuario
 */
@Entity
@Table(name = "prerequisito")

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

    public Prerequisito(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoAsigantura() {
        return codigoAsigantura;
    }

    public void setCodigoAsigantura(Integer codigoAsigantura) {
        this.codigoAsigantura = codigoAsigantura;
    }   

    public Integer getCodigoAsiganturaPrerrequsito() {
        return codigoAsiganturaPrerrequsito;
    }

    public void setCodigoAsiganturaPrerrequsito(Integer codigoAsiganturaPrerrequsito) {
        this.codigoAsiganturaPrerrequsito = codigoAsiganturaPrerrequsito;
    }    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Asignatura getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(Asignatura prerequisito) {
        this.prerequisito = prerequisito;
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
        final Prerequisito other = (Prerequisito) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Prerequisito[ codigo=" + codigo + " ]";
    }
    
}
