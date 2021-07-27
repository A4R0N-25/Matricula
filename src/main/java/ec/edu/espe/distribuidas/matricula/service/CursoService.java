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

import ec.edu.espe.distribuidas.matricula.dao.AsignaturaRepository;
import ec.edu.espe.distribuidas.matricula.dao.CursoRepository;
import ec.edu.espe.distribuidas.matricula.dao.PeriodoRepository;
import ec.edu.espe.distribuidas.matricula.dto.CursoRS;
import ec.edu.espe.distribuidas.matricula.model.Asignatura;
import ec.edu.espe.distribuidas.matricula.model.Curso;
import ec.edu.espe.distribuidas.matricula.model.Periodo;
import ec.edu.espe.distribuidas.matricula.transoform.CursoTS;
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
public class CursoService {
    
    private final CursoRepository cursoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final PeriodoRepository periodoRepository;

    public CursoService(CursoRepository cursoRepository, AsignaturaRepository asignaturaRepository, PeriodoRepository periodoRepository) {
        this.cursoRepository = cursoRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.periodoRepository = periodoRepository;
    }

    
    public List<CursoRS> obtenerCursos(Integer codigoAsignatura, Integer codigoPeriodo){
        Optional<Asignatura> asignatura = this.asignaturaRepository.findById(codigoAsignatura);
        Optional<Periodo> periodo = this.periodoRepository.findById(codigoPeriodo);
        List<CursoRS> cursosRS = new ArrayList<>();
        List<Curso> curso = this.cursoRepository.findByAsignaturaAndPeriodoOrderByNrc(asignatura.get(), periodo.get());
        for(Curso c : curso){
            cursosRS.add(CursoTS.cursoRS(c));
        }
        return cursosRS;
    }
    
    public CursoRS buscarPorNrc(short nrc){
        Curso curso = this.cursoRepository.findByNrc(nrc);
        CursoRS cursoRS = CursoTS.cursoRS(curso);
        return cursoRS;
    }
    
}
