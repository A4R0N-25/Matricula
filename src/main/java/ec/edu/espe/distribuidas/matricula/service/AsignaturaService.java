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

import ec.edu.espe.distribuidas.matricula.dao.AsignaturaPeriodoRepository;
import ec.edu.espe.distribuidas.matricula.dao.DepartamentoRepository;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.model.Asignatura;
import ec.edu.espe.distribuidas.matricula.model.AsignaturaPeriodo;
import ec.edu.espe.distribuidas.matricula.model.Departamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author bran-
 */
@Service
@Slf4j
public class AsignaturaService {

    private final AsignaturaPeriodoRepository asignaturaPeriodoRepository;
    private final DepartamentoRepository departamentoRepository;

    public AsignaturaService(AsignaturaPeriodoRepository asignaturaPeriodoRepository, DepartamentoRepository departamentoRepository) {
        this.asignaturaPeriodoRepository = asignaturaPeriodoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public List<Asignatura> obtenerAsignaturas(Integer codigoDepartamento, Integer codigoPeriodo) {
        List<AsignaturaPeriodo> asignaturaPeriodo = this.asignaturaPeriodoRepository.findByPkCodigoPeriodo(codigoPeriodo);
        if (asignaturaPeriodo.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el asignaturas en este periodo");
        }
        //log.info("Por periodo:{}",asignaturaPeriodo);
        Optional<Departamento> departamento = this.departamentoRepository.findById(codigoDepartamento);

        if (departamento.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el departamento");
        }
        List<Asignatura> asignaturas = new ArrayList<>();
        for (AsignaturaPeriodo asig : asignaturaPeriodo) {
            //log.info("Asignatura: {}",asig.getAsignatura());
            //log.info("Departamento:{}", departamento.get());
            if (asig.getAsignatura().getDepartamento().equals(departamento.get())) {
                asignaturas.add(asig.getAsignatura());
            }
        }
        return asignaturas;
    }

}
