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
package ec.edu.espe.distribuidas.matricula.exception;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author bran-
 */
@Getter
public class MatriculaConflictException extends RuntimeException {

    private List<String> errores;

    public MatriculaConflictException(String message, List<String> errores) {
        super(message);
        this.errores = errores;
    }

}
