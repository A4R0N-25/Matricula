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

import ec.edu.espe.distribuidas.matricula.dto.PeriodoRS;
import ec.edu.espe.distribuidas.matricula.model.Periodo;
import ec.edu.espe.distribuidas.matricula.service.PeriodoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bran-
 */
@RestController
@RequestMapping("/api/periodo/")
@CrossOrigin
public class PeriodoController {
    
    private final PeriodoService periodoService;

    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }
    
    @GetMapping
    public ResponseEntity obtenerPeriodos(){
        List<Periodo> periodos = this.periodoService.obtenerTodosPeriodos();
        List<PeriodoRS> periodosRS = new ArrayList<>();
        periodos.forEach(pr -> {
            periodosRS.add(this.periodoRS(pr));
        });
        return ResponseEntity.ok(periodosRS);
    }
    
    
    private PeriodoRS periodoRS(Periodo periodo){
        PeriodoRS rs = PeriodoRS.builder()
                .codigo(periodo.getCodigo())
                .nombre(periodo.getNombre())
                .build();
        return rs;
    }
}