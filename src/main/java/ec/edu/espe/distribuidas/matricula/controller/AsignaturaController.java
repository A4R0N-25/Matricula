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

import ec.edu.espe.distribuidas.matricula.dto.AsignaturaRS;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.model.Asignatura;
import ec.edu.espe.distribuidas.matricula.service.AsignaturaService;
import ec.edu.espe.distribuidas.matricula.transoform.AsignaturaTS;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/asignatura/")
@CrossOrigin
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @GetMapping(value = "{codigoDepartamento}/{codigoPeriodo}")
    public ResponseEntity obtenerAsignaturas(@PathVariable Integer codigoDepartamento, 
            @PathVariable Integer codigoPeriodo) {
        try {
            List<Asignatura> asignaturas = this.asignaturaService.obtenerAsignaturas(codigoDepartamento, 
                    codigoPeriodo);
            List<AsignaturaRS> asignaturasRS = new ArrayList<>();
            for (Asignatura asig : asignaturas) {
                asignaturasRS.add(AsignaturaTS.asignaturaRS(asig));
            }
            return ResponseEntity.ok(asignaturasRS);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
