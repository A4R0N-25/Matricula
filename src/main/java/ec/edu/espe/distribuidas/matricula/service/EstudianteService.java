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
package ec.edu.espe.distribuidas.matricula.service;

import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */

@Service
public class EstudianteService {
    private final EstudianteService service;

    public EstudianteService(EstudianteService service) {
        this.service = service;
    }    
    
    /*@GetMapping(value="[codigo]")
    public ResponseEntity obtenerEstudainte(@PathVariable("codigo") Integer codigo){
        try{
            ResponseEntity estudiante = this.service.obtenerEstudainte(codigo);
            return ResponseEntity.ok(estudiante);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }*/
    
    
}
