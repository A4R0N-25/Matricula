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

import ec.edu.espe.distribuidas.matricula.model.Asignatura;
import ec.edu.espe.distribuidas.matricula.service.AsignaturaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bran-
 */
@RestController
@RequestMapping("/api/asignatura/")
public class AsignaturaController {
    
    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }
    
    @GetMapping(value = "{codigoDepartamento}/{codigoPeriodo}")
    public ResponseEntity obtenerAsignaturas(@PathVariable Integer codigoDepartamento, @PathVariable Integer codigoPeriodo){
        List<Asignatura> asignaturas = this.asignaturaService.obtenerAsignaturas(codigoDepartamento, codigoPeriodo);
        return ResponseEntity.ok(asignaturas);
    }
    
    
    
}
