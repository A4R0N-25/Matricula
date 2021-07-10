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
import javax.persistence.Table;

/**
 * Representa a la tabla CARRERA_CURSO
 * @author Usuario
 */
@Entity
@Table(name = "carrera_curso")

public class CarreraCurso implements Serializable {

    @EmbeddedId
    private CarreraCursoPK pk;
    
    @Column(name = "estado", nullable = false, length = 3)
    private String estado;

    public CarreraCurso() {
    }

    public CarreraCurso(CarreraCursoPK pk) {
        this.pk = pk;
    }

    public CarreraCurso(CarreraCursoPK pk, String estado) {
        this.pk = pk;
        this.estado = estado;
    }

    public CarreraCurso(Integer codigoCarrera, Integer codigoCurso) {
        this.pk = new CarreraCursoPK(codigoCarrera, codigoCurso);
    }

    public CarreraCursoPK getPk() {
        return pk;
    }

    public void setPk(CarreraCursoPK pk) {
        this.pk = pk;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        final CarreraCurso other = (CarreraCurso) obj;
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "CarreraCurso[ pk=" + pk + " ]";
    }
    
}
