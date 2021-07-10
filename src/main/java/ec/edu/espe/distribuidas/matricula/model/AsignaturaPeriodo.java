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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa a la tabla ASIGNATURA PERIODO
 * @author Usuario
 */
@Entity
@Table(name = "asignatura_periodo")

public class AsignaturaPeriodo implements Serializable {

    @EmbeddedId
    private AsignaturaPeriodoPK pk;
    @Column(name = "estado", length = 3)
    private String estado;
    
    @JoinColumn(name = "cod_asignatura", referencedColumnName = "cod_asignatura", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    
    @JoinColumn(name = "cod_periodo", referencedColumnName = "cod_periodo", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodo periodo;

    public AsignaturaPeriodo() {
    }

    public AsignaturaPeriodo(AsignaturaPeriodoPK pk) {
        this.pk = pk;
    }

    public AsignaturaPeriodo(Integer codigoPeriodo, Integer codigoAsignatura) {
        this.pk = new AsignaturaPeriodoPK(codigoPeriodo, codigoAsignatura);
    }

    public AsignaturaPeriodoPK getPk() {
        return pk;
    }

    public void setPk(AsignaturaPeriodoPK pk) {
        this.pk = pk;
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

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
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
        final AsignaturaPeriodo other = (AsignaturaPeriodo) obj;
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "AsignaturaPeriodo[ pk=" + pk + " ]";
    }
    
}
