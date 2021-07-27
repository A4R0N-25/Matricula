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
package ec.edu.espe.distribuidas.matricula.transoform;

import ec.edu.espe.distribuidas.matricula.dto.CursoRS;
import ec.edu.espe.distribuidas.matricula.dto.HorarioRS;
import ec.edu.espe.distribuidas.matricula.model.CarreraCurso;
import ec.edu.espe.distribuidas.matricula.model.Curso;
import ec.edu.espe.distribuidas.matricula.model.Horario;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author bran-
 */
@Slf4j
public class CursoTS {
    
    public static CursoRS cursoRS(Curso curso){
        return CursoRS.builder()
                .nrc(curso.getNrc())
                .cupo(curso.getCupo())
                .disponible(curso.getDisponible())
                .creditos(curso.getAsignatura().getCreditos())
                .carreras(carrerasCusrso(curso.getCarreraCursos()))
                .horarios(horarios(curso.getHorarios()))
                .build();
    }
    
    public static List<String> carrerasCusrso(List<CarreraCurso> carreraCurso){
        List<String> carreras = new ArrayList<>();
        if(carreraCurso.isEmpty() || carreraCurso==null){
            return null;
        }
        for(CarreraCurso c : carreraCurso){
            log.info("Nombre Carrera:{}",c.getCarrera().getNombre());
            carreras.add(c.getCarrera().getNombre());
        }
        return carreras;
    }
    
    public static List<HorarioRS> horarios (List<Horario> h){
        List<HorarioRS> horarioRS= new ArrayList<>();
        if(h.isEmpty()){
            return null;
        }
        for(Horario hor: h){
            HorarioRS hrs= new HorarioRS();
            hrs.setDia(hor.getDia());
            hrs.setHoraInicio(hor.getHoraInicio());
            hrs.setHoraFin(hor.getHoraFin());
            log.info("Horario:{}",hrs);
            horarioRS.add(hrs);
        }
        return horarioRS;
    }
    
}
