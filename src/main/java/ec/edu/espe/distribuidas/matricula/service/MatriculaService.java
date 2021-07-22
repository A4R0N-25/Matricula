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

import ec.edu.espe.distribuidas.matricula.dao.CursoRepository;
import ec.edu.espe.distribuidas.matricula.dao.EstudianteRepository;
import ec.edu.espe.distribuidas.matricula.dao.MatriculaRepository;
import ec.edu.espe.distribuidas.matricula.dto.MatriculaRQ;
import ec.edu.espe.distribuidas.matricula.model.CarreraCurso;
import ec.edu.espe.distribuidas.matricula.model.Curso;
import ec.edu.espe.distribuidas.matricula.model.DetalleMatricula;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import ec.edu.espe.distribuidas.matricula.model.Horario;
import ec.edu.espe.distribuidas.matricula.model.Matricula;
import ec.edu.espe.distribuidas.matricula.model.Prerequisito;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bran-
 */
@Service
@Slf4j
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public void matricularse(MatriculaRQ matriculaRQ) {

        Matricula matricula = new Matricula();
        List<DetalleMatricula> detalleMatriculas = new ArrayList<>();

        Estudiante estudiante = this.estudianteRepository.findById(matriculaRQ.getEstudiante()).get();

        matricula.setCodigoEstudiante(matriculaRQ.getEstudiante());
        matricula.setCodigoPeriodo(matriculaRQ.getPeriodo());
        Integer creditos = 0;
        for (Integer crs : matriculaRQ.getCursos()) {
            log.info("Buscando Curso");
            Curso curso = this.cursoRepository.findById(crs).get();
            if (curso.getDisponible() != 0 && creditos < 50) {
                log.info("Disponible");
                if (creditos + curso.getAsignatura().getCreditos() <= 50) {
                    log.info("creditos correctos");
                    if (curso.getAsignatura().getPrerequisitos().isEmpty()) {
                        log.info("sin prerequisitos");
                        for (CarreraCurso carreraCurso : curso.getCarreraCursos()) {
                            if (carreraCurso.getCarrera().equals(estudiante.getCarrera())) {
                                log.info("Carrera habilitada");
                                for (Horario horario : curso.getHorarios()) {
                                    if (detalleMatriculas.isEmpty()) {
                                        DetalleMatricula detalleMatriculaNueva = new DetalleMatricula();
                                        detalleMatriculaNueva.setCurso(curso);
                                        detalleMatriculaNueva.setEstado("REP");
                                        detalleMatriculaNueva.setFecha(new Date());
                                        creditos += curso.getAsignatura().getCreditos();
                                        log.info("Nueva materia:{}",detalleMatriculaNueva);
                                        detalleMatriculas.add(detalleMatriculaNueva);

                                    } else {
                                        for (DetalleMatricula detalleMatricula : detalleMatriculas) {
                                            for (Horario detalleMatriculaHorario : detalleMatricula.getCurso().getHorarios()) {
                                                if (horario.getDia().equals(detalleMatriculaHorario.getDia())) {
                                                    log.info("Dias iguales");
                                                    if ((horario.getHoraFin().before(detalleMatriculaHorario.getHoraInicio()) || horario.getHoraInicio().after(detalleMatriculaHorario.getHoraFin()))) {
                                                        log.info("Horarios distintos");
                                                        DetalleMatricula detalleMatriculaNueva = new DetalleMatricula();
                                                        detalleMatriculaNueva.setCurso(curso);
                                                        detalleMatriculaNueva.setEstado("REP");
                                                        detalleMatriculaNueva.setFecha(new Date());
                                                        creditos += curso.getAsignatura().getCreditos();
                                                        log.info("Nueva materia:{}",detalleMatriculaNueva);
                                                        detalleMatriculas.add(detalleMatriculaNueva);
                                                    } else {
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    } else {
                        log.info("con prerequisitos");
                        for (Prerequisito prerequisito : curso.getAsignatura().getPrerequisitos()) {
                            for (Matricula matriculaEstudiante : estudiante.getMatricula()) {
                                for (DetalleMatricula detalleMatriculaEstudiante : matriculaEstudiante.getDetalle()) {
                                    if (prerequisito.getPrerequisito().equals(detalleMatriculaEstudiante.getCurso().getAsignatura()) && detalleMatriculaEstudiante.getEstado().equals("APR")) {
                                        for (CarreraCurso carreraCurso : curso.getCarreraCursos()) {
                                            if (carreraCurso.getCarrera().equals(estudiante.getCarrera())) {
                                                for (Horario horario : curso.getHorarios()) {
                                                    for (DetalleMatricula detalleMatricula : detalleMatriculas) {
                                                        for (Horario detalleMatriculaHorario : detalleMatricula.getCurso().getHorarios()) {
                                                            if (horario.getDia().equals(detalleMatriculaHorario.getDia())) {
                                                                if ((horario.getHoraFin().before(detalleMatriculaHorario.getHoraInicio()) || horario.getHoraInicio().after(detalleMatriculaHorario.getHoraFin()))) {
                                                                    DetalleMatricula detalleMatriculaNueva = new DetalleMatricula();
                                                                    detalleMatriculaNueva.setCurso(curso);
                                                                    detalleMatriculaNueva.setEstado("REP");
                                                                    detalleMatriculaNueva.setFecha(new Date());
                                                                    creditos += curso.getAsignatura().getCreditos();
                                                                    log.info("Nueva materia:{}",detalleMatriculaNueva);
                                                                    detalleMatriculas.add(detalleMatriculaNueva);
                                                                } else {
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }
        matricula.setCreditosTotales(BigDecimal.valueOf(creditos));
        log.info("Detalles Matriculas: {}",detalleMatriculas);
        matricula.setDetalle(detalleMatriculas);
        matricula.setFecha(new Date());
        this.matriculaRepository.save(matricula);

    }
}
