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
package ec.edu.espe.distribuidas.matricula.service;

import ec.edu.espe.distribuidas.matricula.dao.EstudianteRepository;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author bran-
 */
@Service
public class EstudianteService {
    
    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }
    
    public Estudiante obtenerEstudanterPorCorreo(String correo){
        Optional<Estudiante> estudianteOpt = this.estudianteRepository.findByCorreo(correo);
        
        if(estudianteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el estudiante con el correo: " + correo);
        }
        
        return estudianteOpt.get();
    }
}
