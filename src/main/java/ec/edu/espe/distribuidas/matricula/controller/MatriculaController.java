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

import ec.edu.espe.distribuidas.matricula.dto.MatriculaRQ;
import ec.edu.espe.distribuidas.matricula.dto.MatriculaRS;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.exception.MatriculaConflictException;
import ec.edu.espe.distribuidas.matricula.model.Matricula;
import ec.edu.espe.distribuidas.matricula.service.MatriculaService;
import ec.edu.espe.distribuidas.matricula.transoform.MatriculaTS;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bran-
 */
@RestController
@RequestMapping("/api/matricula/")
@CrossOrigin
@Slf4j
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity matricularse(@RequestBody MatriculaRQ matriculaRQ) {
        try {
            return ResponseEntity.ok(this.matriculaService.matricularse(matriculaRQ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MatriculaConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrores());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity buscarMatricula(@RequestParam String correo, @RequestParam Integer periodo) {
        try {
            Matricula matricula = this.matriculaService.buscarMatricula(correo, periodo);
            MatriculaRS matriculaRS = MatriculaTS.matriculaRS(matricula);
            return ResponseEntity.ok(matriculaRS);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "{correo}")
    public ResponseEntity buscarMatriculasPorEstudiante(@PathVariable String correo) {
        try {
            List<Matricula> matriculas = this.matriculaService.obtenerMatriculasPorEstudiante(correo);
            List<MatriculaRS> matriculasRS = new ArrayList<>();
            for (Matricula matricula : matriculas) {
                MatriculaRS matriculaRS = MatriculaTS.matriculaRS(matricula);
                matriculasRS.add(matriculaRS);
            }
            return ResponseEntity.ok(matriculasRS);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity borarrMatriculaDetalle(@PathVariable Integer id) {
        try {
            this.matriculaService.borrarDetalleMatricula(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }
}
