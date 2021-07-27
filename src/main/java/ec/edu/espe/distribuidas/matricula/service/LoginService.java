package ec.edu.espe.distribuidas.matricula.service;

import ec.edu.espe.distribuidas.matricula.dao.EstudianteRepository;
import ec.edu.espe.distribuidas.matricula.dto.LoginDto;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final EstudianteRepository estudianteRepository;


    public LoginService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public boolean authenticacion(LoginDto loginDto){
        Estudiante estudiante = this.estudianteRepository.findByCorreo(loginDto.getCorreo());
        if (estudiante!=null){
            if (estudiante.getContraseña().equals(loginDto.getPassword())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
