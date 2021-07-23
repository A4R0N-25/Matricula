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

import ec.edu.espe.distribuidas.matricula.model.DetalleMatricula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bran-
 */
public interface DetalleMatriculaRepository extends JpaRepository<DetalleMatricula, Integer>{
    
    @Query(value = "SELECT * FROM detalle_matricula d,matricula m, estudiante e where d.cod_matricula =m.cod_matricula and m.cod_estudiante=e.cod_estudiante and e.cod_estudiante = 1", nativeQuery = true)
    List<DetalleMatricula> findByEstudiante(Integer estudiante);
    
}
