/*
 * Copyright (c) 2021 Usuario.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Usuario - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.matricula.service;

import ec.edu.espe.distribuidas.matricula.dao.DepartamentoRepository;
import ec.edu.espe.distribuidas.matricula.model.Departamento;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */

@Service
public class DepartamentoService {
    
    private final DepartamentoRepository departamentoRepo;
    
    public DepartamentoService(DepartamentoRepository departamentoRepo){
        
        this.departamentoRepo = departamentoRepo;
    }
    
    public Departamento obtainByCodigo(Integer codigo){
        Optional<Departamento> departamentoOpt = this.departamentoRepo.findById(codigo);
        if(departamentoOpt.isPresent()){
            return departamentoOpt.get();
        }else{
            throw new RuntimeException("No found");
        }
    }
    
    public Departamento ObtainByNombre(String nombre){
        return this.departamentoRepo.findByNombreLike(nombre);
    }
}
