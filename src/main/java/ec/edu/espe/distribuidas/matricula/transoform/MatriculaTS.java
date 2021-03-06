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

import ec.edu.espe.distribuidas.matricula.dto.MatriculaDetalleRS;
import ec.edu.espe.distribuidas.matricula.dto.MatriculaRS;
import ec.edu.espe.distribuidas.matricula.model.DetalleMatricula;
import ec.edu.espe.distribuidas.matricula.model.Matricula;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bran-
 */
public class MatriculaTS {

    public static MatriculaRS matriculaRS(Matricula matricula) {
        MatriculaRS matriculaRS = new MatriculaRS();
        matriculaRS.setCodigo(matricula.getCodigo());
        matriculaRS.setFecha(matricula.getFecha());
        matriculaRS.setCreditosTotales(matricula.getCreditosTotales());
        matriculaRS.setPeriodo(matricula.getPeriodo().getNombre());
        List<MatriculaDetalleRS> matriculaDetalles = new ArrayList<>();
        for (DetalleMatricula detalleMatricula : matricula.getDetalle()) {
            MatriculaDetalleRS matriculaDetalleRS = MatriculaDetalleRS.builder()
                    .codigo(detalleMatricula.getCodigo())
                    .nrc(detalleMatricula.getCurso().getNrc())
                    .materia(detalleMatricula.getCurso().getAsignatura().getNombre())
                    .creditos(detalleMatricula.getCurso().getAsignatura().getCreditos())
                    .estado(detalleMatricula.getEstado())
                    .build();
            matriculaDetalles.add(matriculaDetalleRS);
        }
        matriculaRS.setDetalle(matriculaDetalles);
        return matriculaRS;
    }

}
