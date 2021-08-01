package ec.edu.espe.distribuidas.matricula.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import ec.edu.espe.distribuidas.matricula.dao.CarreraRepository;
import ec.edu.espe.distribuidas.matricula.dao.EstudianteRepository;
import ec.edu.espe.distribuidas.matricula.dto.EstudianteEditarRS;
import ec.edu.espe.distribuidas.matricula.dto.EstudianteRQ;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.model.Carrera;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;

    public EstudianteService(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    public void agregarEstudiante(EstudianteRQ estudianteRQ) {
        boolean ced = this.verificarCedula(estudianteRQ.getIdentificacion());
        
        if(!ced){
            log.error("Cedula Invalida: {}",estudianteRQ.getIdentificacion());
            throw new EntityNotFoundException("Cedula Invalida");
        }
        
        List<Estudiante> estudiantes = this.estudianteRepository
                .findByCorreoOrIdentificacion(estudianteRQ.getCorreo() + "@espe.edu.ec", estudianteRQ.getIdentificacion());
        if (!estudiantes.isEmpty()) {
            throw new EntityExistsException("Ya existe un usuario con esta cedula o nombre de usuario");
        }
        Optional<Carrera> carrera = this.carreraRepository.findById(estudianteRQ.getCarrera());
        if (carrera.isEmpty()) {
            throw new EntityNotFoundException("No existe la carrera con id: " + estudianteRQ.getCarrera());
        }
        Estudiante estudiante = new Estudiante();
        estudiante.setTipoIdentificacion(estudianteRQ.getTipo());
        estudiante.setIdentificacion(estudianteRQ.getIdentificacion());
        estudiante.setContraseña(estudianteRQ.getContraseña());
        estudiante.setCorreo(estudianteRQ.getCorreo() + "@espe.edu.ec");
        estudiante.setApellido(estudianteRQ.getApellido().toUpperCase());
        estudiante.setNombre(estudianteRQ.getNombre().toUpperCase());
        estudiante.setGenero(estudianteRQ.getGenero());
        estudiante.setTelefono(estudianteRQ.getTelefono());
        estudiante.setDireccion(estudianteRQ.getDireccion().toUpperCase());
        estudiante.setCarrera(carrera.get());
        this.estudianteRepository.save(estudiante);
    }

    public Estudiante actualizarEstudiante(String usuario, EstudianteEditarRS estudianteRs) {
        Optional<Estudiante> estudianteOptional = this.estudianteRepository.findByCorreo(usuario);
        if (estudianteOptional.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el estudiante con el usuario: " + usuario);
        }
        Estudiante estudiante = estudianteOptional.get();
        estudiante.setApellido(estudianteRs.getApellido().toUpperCase());
        estudiante.setNombre(estudianteRs.getNombre().toUpperCase());
        estudiante.setGenero(estudianteRs.getGenero());
        estudiante.setTelefono(estudianteRs.getTelefono());
        estudiante.setDireccion(estudianteRs.getDireccion().toUpperCase());
        return this.estudianteRepository.save(estudiante);
    }

    public Estudiante obtenerEstudanterPorCorreo(String correo) {
        log.info(correo);
        Optional<Estudiante> estudianteOpt = this.estudianteRepository.findByCorreo(correo);
        log.info("Nombre: {}", estudianteOpt.get().getApellido());
        if (estudianteOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el estudiante con el correo: " + correo);
        }

        return estudianteOpt.get();
    }

    private boolean verificarCedula(String document) {
        byte sum = 0;
        try {
            if (document.trim().length() != 10) {
                return false;
            }
            String[] data = document.split("");
            byte verifier = Byte.parseByte(data[0] + data[1]);
            if (verifier < 1 || verifier > 24) {
                return false;
            }
            byte[] digits = new byte[data.length];
            for (byte i = 0; i < digits.length; i++) {
                digits[i] = Byte.parseByte(data[i]);
            }
            if (digits[2] > 6) {
                return false;
            }
            for (byte i = 0; i < digits.length - 1; i++) {
                if (i % 2 == 0) {
                    verifier = (byte) (digits[i] * 2);
                    if (verifier > 9) {
                        verifier = (byte) (verifier - 9);
                    }
                } else {
                    verifier = (byte) (digits[i] * 1);
                }
                sum = (byte) (sum + verifier);
            }
            if ((sum - (sum % 10) + 10 - sum) == digits[9]) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
