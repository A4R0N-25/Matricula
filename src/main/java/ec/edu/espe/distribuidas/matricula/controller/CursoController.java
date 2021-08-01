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

import ec.edu.espe.distribuidas.matricula.dto.CursoRS;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.service.CursoService;
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
@RequestMapping("/api/curso/")
@CrossOrigin
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping(value = "{codigoAsignatura}/{codigoPeriodo}")
    public ResponseEntity obneterCursos(@PathVariable Integer codigoAsignatura, @PathVariable Integer codigoPeriodo) {
        try {
            List<CursoRS> cursos = this.cursoService.obtenerCursos(codigoAsignatura, codigoPeriodo);
            return ResponseEntity.ok(cursos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "{nrc}")
    public ResponseEntity buscarNRC(@PathVariable short nrc) {
        try {
            CursoRS cursoRS = this.cursoService.buscarPorNrc(nrc);
            return ResponseEntity.ok(cursoRS);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
