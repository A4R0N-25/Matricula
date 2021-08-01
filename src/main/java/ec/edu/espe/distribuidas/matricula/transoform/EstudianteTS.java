/*
 * Copyright (c) 2021 bran-.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    bran- - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.matricula.transoform;

import ec.edu.espe.distribuidas.matricula.dto.EstudianteRS;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;


/**
 *
 * @author bran-
 */
public class EstudianteTS {
    
    public static EstudianteRS estudianteRS(Estudiante estudiante){
        EstudianteRS estudianteRS = EstudianteRS.builder()
                .tipo(estudiante.getIdentificacion())
                .identificacion(estudiante.getTipoIdentificacion())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .correo(estudiante.getCorreo())
                .genero(estudiante.getGenero())
                .telefono(estudiante.getTelefono())
                .direccion(estudiante.getDireccion())
                .build();
        return estudianteRS;
    }
    
}
