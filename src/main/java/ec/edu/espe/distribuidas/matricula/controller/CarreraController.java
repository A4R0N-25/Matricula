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
package ec.edu.espe.distribuidas.matricula.controller;

import ec.edu.espe.distribuidas.matricula.model.Carrera;
import ec.edu.espe.distribuidas.matricula.service.CarreraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */

@RestController
@RequestMapping("/api/carrera")
public class CarreraController {
    
    private final CarreraService service;

    public CarreraController(CarreraService service) {
        this.service = service;
    }
    
    @GetMapping(value="{codigo}")
    public ResponseEntity obtenerCarrera(@PathVariable("codigo") Integer codigo){
        try{
            Carrera carrera = this.service.obtainByCodigo(codigo);
            return ResponseEntity.ok(carrera);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
}
