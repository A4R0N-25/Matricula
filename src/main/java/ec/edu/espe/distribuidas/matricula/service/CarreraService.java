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

import ec.edu.espe.distribuidas.matricula.dao.CarreraRepository;
import ec.edu.espe.distribuidas.matricula.dao.DepartamentoRepository;
import ec.edu.espe.distribuidas.matricula.model.Carrera;
import ec.edu.espe.distribuidas.matricula.model.Departamento;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Usuario
 */

@Service
public class CarreraService {
    
    private final CarreraRepository carreraRepo;
    private final DepartamentoRepository departamentoRepo;

    public CarreraService(CarreraRepository carreraRepo, DepartamentoRepository departamentoRepo) {
        this.carreraRepo = carreraRepo;
        this.departamentoRepo = departamentoRepo;
    }
    
    public Carrera obtainByCodigo(Integer codigo){
        Optional<Carrera> carreraOpt = this.carreraRepo.findById(codigo);
        if(carreraOpt.isPresent()){
            return carreraOpt.get();
        }else{
            throw new RuntimeException("No found");
        }
    }
    
    @Transactional
    public void createCarrera(Carrera carrera){
        this.carreraRepo.save(carrera);
    }
    
    @Transactional
    public void modifyCarrera(Carrera carrera){
        this.carreraRepo.save(carrera);
    }  
    
    @Transactional
    public List<Carrera> listAll(){
        return this.carreraRepo.findAll();
    }
    
}
