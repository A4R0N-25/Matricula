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

import ec.edu.espe.distribuidas.matricula.dto.PeriodoRS;
import ec.edu.espe.distribuidas.matricula.model.Periodo;

/**
 *
 * @author bran-
 */
public class PeriodoTS {

    public static PeriodoRS periodoRS(Periodo periodo) {
        PeriodoRS rs = PeriodoRS.builder()
                .codigo(periodo.getCodigo())
                .nombre(periodo.getNombre())
                .estado(periodo.getEstado())
                .build();
        return rs;
    }

}
