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
 * Representa a la clave primaria de la tabla CARRERA_CURSO
 * @author Usuario
 */
@Embeddable
public class CarreraCursoPK implements Serializable {

    @Column(name = "cod_carrera", nullable = false)
    private Integer codigoCarrera;

    @Column(name = "cod_curso", nullable = false)
    private Integer codigoCurso;

    public CarreraCursoPK() {
    }

    public CarreraCursoPK(Integer codigoCarrera, Integer codigoCurso) {
        this.codigoCarrera = codigoCarrera;
        this.codigoCurso = codigoCurso;
    }

    public int getCodCarrera() {
        return codigoCarrera;
    }

    public void setCodCarrera(Integer codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public int getCodCurso() {
        return codigoCurso;
    }

    public void setCodCurso(Integer codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoCarrera;
        hash += (int) codigoCurso;
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
        final CarreraCursoPK other = (CarreraCursoPK) obj;
        if (!Objects.equals(this.codigoCarrera, other.codigoCarrera)) {
            return false;
        }
        if (!Objects.equals(this.codigoCurso, other.codigoCurso)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "CarreraCursoPK[ codigoCarrera=" + codigoCarrera + ", codigoCurso=" + codigoCurso + " ]";
    }
    
}
