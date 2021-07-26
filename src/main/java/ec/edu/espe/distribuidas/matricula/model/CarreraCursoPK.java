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
import lombok.Data;

/**
 * Representa a la clave primaria de la tabla CARRERA_CURSO
 * @author Usuario
 */
@Embeddable
@Data
public class CarreraCursoPK implements Serializable {

    @Column(name = "cod_carrera", nullable = false)
    private Integer codigoCarrera;

    @Column(name = "cod_curso", nullable = false)
    private Integer codigoCurso;

    public CarreraCursoPK() {
    }
    
    
    
    
}
