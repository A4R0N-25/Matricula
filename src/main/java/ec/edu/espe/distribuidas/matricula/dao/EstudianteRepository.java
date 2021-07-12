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

import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bran-
 */
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
    
    Estudiante findByTipoIdentificacionAndIdentificacion(String tipoIdentificacion, String identificacion);
    
    List<Estudiante> findByApellidoStartingWithOredrByApellido(String apellido);
    
    List<Estudiante> findByTelefonoOrderByApellidoAndNombre(String telefono);
    
    List<Estudiante> findByGeneroOrderByApellidoAndNombre(String genero);
    
}
