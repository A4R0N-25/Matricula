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
import ec.edu.espe.distribuidas.matricula.dao.DetalleMatriculaRepository;
import ec.edu.espe.distribuidas.matricula.dao.EstudianteRepository;
import ec.edu.espe.distribuidas.matricula.dao.MatriculaRepository;
import ec.edu.espe.distribuidas.matricula.dao.PeriodoRepository;
import ec.edu.espe.distribuidas.matricula.dto.MatriculaRQ;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.model.CarreraCurso;
import ec.edu.espe.distribuidas.matricula.model.Curso;
import ec.edu.espe.distribuidas.matricula.model.DetalleMatricula;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import ec.edu.espe.distribuidas.matricula.model.Horario;
import ec.edu.espe.distribuidas.matricula.model.Matricula;
import ec.edu.espe.distribuidas.matricula.model.Periodo;
import ec.edu.espe.distribuidas.matricula.model.Prerequisito;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    private final PeriodoRepository periodoRepository;
    private final DetalleMatriculaRepository detalleMatriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, EstudianteRepository estudianteRepository, CursoRepository cursoRepository, PeriodoRepository periodoRepository, DetalleMatriculaRepository detalleMatriculaRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
        this.periodoRepository = periodoRepository;
        this.detalleMatriculaRepository = detalleMatriculaRepository;
    }

    private DetalleMatricula verificarHorario(List<String> errorCursos, List<DetalleMatricula> detalleMatriculas, Matricula matricula, Curso curso, Integer creditos) {
        for (DetalleMatricula detalleMatricula : detalleMatriculas) {
            for (Horario horarioActual : curso.getHorarios()) {
                List<Horario> horariosChoque = detalleMatricula.getCurso().getHorarios()
                        .stream().filter(horario -> horario.getDia().equals(horarioActual.getDia())
                        && horario.getHoraInicio().after(horarioActual.getHoraFin()) && horario.getHoraFin().before(horarioActual.getHoraInicio()))
                        .collect(Collectors.toList());
                if (!horariosChoque.isEmpty()) {
                    log.error("El curso:" + curso.getNrc() + " se choca con uno o mas cursos actuales");
                    errorCursos.add("El curso:" + curso.getNrc() + " se choca con uno o mas cursos actuales");
                    return null;
                }
            }
        }

        DetalleMatricula detalleMatriculaNueva = new DetalleMatricula();
        detalleMatriculaNueva.setMatricula(matricula);
        detalleMatriculaNueva.setCurso(curso);
        detalleMatriculaNueva.setEstado("REP");
        detalleMatriculaNueva.setFecha(new Date());
        creditos += curso.getAsignatura().getCreditos();
        log.info("Creditos:{}", creditos);
        curso.setCupo((short) (curso.getCupo() - 1));
        //log.info("Nueva materia:{}",detalleMatriculaNueva.getCurso().getAsignatura().getNombre());
        return detalleMatriculaNueva;
    }

    @Transactional
    public void matricularse(MatriculaRQ matriculaRQ) {
        Matricula matricula;
        if(matriculaRQ.getMatricula() == null) {
            matricula = new Matricula();
        } else {
            Optional<Matricula> matriculaOptional = matriculaRepository.findById(matriculaRQ.getMatricula());
            if(matriculaOptional.isPresent()) {
                matricula = matriculaOptional.get();
            } else {
                matricula = new Matricula();
            }
        }
        List<DetalleMatricula> detalleMatriculas = new ArrayList<>();

        Estudiante estudiante = this.estudianteRepository.findByCorreo(matriculaRQ.getCorreo());

        Optional<Periodo> periodo = this.periodoRepository.findById(matriculaRQ.getPeriodo());
        if (periodo.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el periodo con el ID" + matriculaRQ.getPeriodo());
        }
        Integer creditos = 0;
        List<String> errorCursos = new ArrayList<>();
        for (Integer crs : matriculaRQ.getCursos()) {
            Optional<Curso> cursoOpt = this.cursoRepository.findById(crs);
            if (cursoOpt.isEmpty()) {
                errorCursos.add("El curso con el ID: " + crs + " no existe");
                continue;
            }
            Curso curso = cursoOpt.get();
            log.info("curso periodo:{}",curso.getPeriodo());
            log.info("periodo matricula:_{}",periodo.get());
            if(!curso.getPeriodo().equals(periodo.get())){
                errorCursos.add("El curso con el ID: " + crs + " no pertenece al mismo periodo");
                continue;
            }
            if (creditos + curso.getAsignatura().getCreditos() > 50) {
                errorCursos.add("No dispone de los creditos necesarios para matricularse en el curso:" + curso.getNrc());
                log.error("No dispone de los creditos necesarios para matricularse en el curso:" + curso.getNrc());
                continue;
            }
            if (curso.getDisponible() == 0) {
                errorCursos.add("El curso:" + curso.getNrc() + " no cuenta con cupos disponibles");
                log.error("El curso:" + curso.getNrc() + " no cuenta con cupos disponibles");
                continue;
            }

            boolean carrera = false;
            boolean prerequisito = false;
            int nPre = 0;

            List<DetalleMatricula> materiasEstudiante = this.detalleMatriculaRepository.findByEstudiante(estudiante.getCodigo());

            for (Prerequisito pre : curso.getAsignatura().getPrerequisitos()) {
                for (DetalleMatricula detMatricula : materiasEstudiante) {
                    if (pre.getPrerequisito().equals(detMatricula.getCurso().getAsignatura())) {
                        nPre++;
                        break;
                    }
                }

            }
            if (curso.getAsignatura().getPrerequisitos().size() == nPre) {
                prerequisito = true;
            } else {
                log.error("No cumplio con los prerequisitos");
                errorCursos.add("No cumple con los prerequisitos para el curso:"+curso.getNrc());
            }

            for (CarreraCurso carreraCurso : curso.getCarreraCursos()) {
                if (carreraCurso.getCarrera().equals(estudiante.getCarrera())) {
                    carrera = true;
                    break;
                }
            }
            if (!carrera) {
                log.error("Carrera no disponible");
                errorCursos.add("Su carrera no esta contemplada en el curso:"+curso.getNrc());
            }

            boolean ver = false;
            if ((curso.getAsignatura().getPrerequisitos().isEmpty() || prerequisito) && (curso.getCarreraCursos().isEmpty() || carrera)) {
                for (DetalleMatricula detalleMatricula : detalleMatriculas) {
                    for (Horario horarioActual : curso.getHorarios()) {
                        List<Horario> horariosChoque = detalleMatricula.getCurso().getHorarios()
                                .stream().filter(horario -> horario.getDia().equals(horarioActual.getDia())
                                && horario.getHoraInicio().after(horarioActual.getHoraFin()) && horario.getHoraFin().before(horarioActual.getHoraInicio()))
                                .collect(Collectors.toList());
                        if (!horariosChoque.isEmpty()) {
                            log.error("El curso:" + curso.getNrc() + " se choca con uno o mas cursos actuales");
                            errorCursos.add("El curso:" + curso.getNrc() + " se choca con uno o mas cursos actuales");
                            ver = true;
                            break;
                        }
                    }
                    if (ver) {
                        break;
                    }
                }
                if (!ver) {
                    DetalleMatricula detalleMatriculaNueva = new DetalleMatricula();
                    detalleMatriculaNueva.setMatricula(matricula);
                    detalleMatriculaNueva.setCurso(curso);
                    detalleMatriculaNueva.setEstado("REP");
                    detalleMatriculaNueva.setFecha(new Date());
                    creditos += curso.getAsignatura().getCreditos();
                    log.info("Creditos:{}", creditos);
                    curso.setCupo((short) (curso.getCupo() - 1));

                    detalleMatriculas.add(detalleMatriculaNueva);
                }

            }
        }
        
        matricula.setEstudiante(estudiante);

        log.info("CreditosDes:{}", creditos);
        log.info("Errores:{}", errorCursos);
        matricula.setCreditosTotales(BigDecimal.valueOf(creditos));
        matricula.setDetalle(detalleMatriculas);
        matricula.setPeriodo(periodo.get());
        /*for(DetalleMatricula d : matricula.getDetalle()){
            d.setMatricula(matricula);
        }*/
        //log.info("Detalles Matriculas: {}",matricula);
        matricula.setFecha(new Date());
        if (detalleMatriculas.size() > 0) {
            this.matriculaRepository.save(matricula);
        }else{
            errorCursos.add("No se logro matricular en ninguna materia");
        }

    }
}
