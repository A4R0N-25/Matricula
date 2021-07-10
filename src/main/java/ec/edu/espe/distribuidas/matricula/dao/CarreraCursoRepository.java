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
package ec.edu.espe.distribuidas.matricula.dao;

import ec.edu.espe.distribuidas.matricula.model.CarreraCurso;
import ec.edu.espe.distribuidas.matricula.model.CarreraCursoPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bran-
 */
public interface CarreraCursoRepository extends JpaRepository<CarreraCurso, CarreraCursoPK>{
    
}
