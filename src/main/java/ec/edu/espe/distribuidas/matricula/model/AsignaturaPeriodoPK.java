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
import javax.persistence.Embeddable;

/**
 * Representa a la clave primaria de la tabla ASIGNATURA_PERIODO
 * @author Usuario
 */
@Embeddable
public class AsignaturaPeriodoPK implements Serializable {

    @Column(name = "cod_periodo", nullable = false)
    private Integer codigoPeriodo;

    @Column(name = "cod_asignatura", nullable = false)
    private Integer codigoAsignatura;

    public AsignaturaPeriodoPK() {
    }

    public AsignaturaPeriodoPK(Integer codigoPeriodo, Integer codigoAsignatura) {
        this.codigoPeriodo = codigoPeriodo;
        this.codigoAsignatura = codigoAsignatura;
    }

    public Integer getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(Integer codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public Integer getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(Integer codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPeriodo;
        hash += (int) codigoAsignatura;
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
        final AsignaturaPeriodoPK other = (AsignaturaPeriodoPK) obj;
        if (!Objects.equals(this.codigoPeriodo, other.codigoPeriodo)) {
            return false;
        }
        if (!Objects.equals(this.codigoAsignatura, other.codigoAsignatura)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "AsignaturaPeriodoPK[ codigoPeriodo=" + codigoPeriodo + ", codigoAsignatura=" + codigoAsignatura + " ]";
    }
    
}
