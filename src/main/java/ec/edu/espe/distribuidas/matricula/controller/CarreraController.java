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

import ec.edu.espe.distribuidas.matricula.dto.CarreraRS;
import ec.edu.espe.distribuidas.matricula.model.Carrera;
import ec.edu.espe.distribuidas.matricula.service.CarreraService;
import ec.edu.espe.distribuidas.matricula.transoform.CarreraTS;
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
@RequestMapping("/api/carrera")
@CrossOrigin
public class CarreraController {

    public final CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    private ResponseEntity obtenerCarreras() {
        try {
            List<Carrera> carreras = this.carreraService.obtenerTodasCarreras();
            List<CarreraRS> carrerasRS = new ArrayList<>();
            for (Carrera carrera : carreras) {
                CarreraRS carreraRS = CarreraTS.carreraTransform(carrera);
                carrerasRS.add(carreraRS);
            }
            return ResponseEntity.ok(carrerasRS);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
