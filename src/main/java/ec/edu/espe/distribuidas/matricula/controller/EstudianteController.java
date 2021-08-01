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
package ec.edu.espe.distribuidas.matricula.controller;

import ec.edu.espe.distribuidas.matricula.dto.EstudianteRS;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import ec.edu.espe.distribuidas.matricula.service.EstudianteService;
import ec.edu.espe.distribuidas.matricula.transoform.EstudianteTS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bran-
 */
@RestController
@RequestMapping("/api/estudiante/")
@CrossOrigin
public class EstudianteController {
    
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }
    
    @GetMapping(value = "{correo}")
    public ResponseEntity obtenerDatosEstudiante(@PathVariable String correo){
        try {
            Estudiante estudiante = this.estudianteService.obtenerEstudanterPorCorreo(correo);
            EstudianteRS estudianteRS = EstudianteTS.estudianteRS(estudiante);
            return ResponseEntity.ok(estudianteRS);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }
  
  @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody EstudianteRS estudianteRs) {
        try {
            return ResponseEntity.ok(this.estudianteService.agregarEstudiante(estudianteRs));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<Estudiante> editarEstudiante(@PathVariable String usuario, @RequestBody EstudianteEditarRS estudianteEditarRs) {
        try {
            return ResponseEntity.ok(this.estudianteService.actualizarEstudiante(usuario, estudianteEditarRs));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
