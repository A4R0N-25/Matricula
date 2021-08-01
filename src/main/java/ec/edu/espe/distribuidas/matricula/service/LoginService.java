package ec.edu.espe.distribuidas.matricula.service;

import ec.edu.espe.distribuidas.matricula.dao.EstudianteRepository;
import ec.edu.espe.distribuidas.matricula.dto.LoginDto;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final EstudianteRepository estudianteRepository;

    public LoginService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void authenticacion(LoginDto loginDto) throws Exception {
        Optional<Estudiante> estudianteOpt = this.estudianteRepository.findByCorreo(loginDto.getCorreo());
        if (estudianteOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Estudiante estudiante = estudianteOpt.get();

        if (!estudiante.getContraseña().equals(loginDto.getPassword())) {
            throw new Exception("Contraseña Invalida");
        }
    }
}
