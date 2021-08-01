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

    public Estudiante agregarEstudiante(EstudianteRQ estudianteRQ) {
        List<Estudiante> estudiantes = this.estudianteRepository
                .findByCorreoOrIdentificacion(estudianteRQ.getCorreo()+ "@espe.edu.ec", estudianteRQ.getIdentificacion());
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
        estudiante.setCorreo(estudianteRQ.getCorreo()+"@espe.edu.ec");
        estudiante.setApellido(estudianteRQ.getApellido().toUpperCase());
        estudiante.setNombre(estudianteRQ.getNombre().toUpperCase());
        estudiante.setGenero(estudianteRQ.getGenero());
        estudiante.setTelefono(estudianteRQ.getTelefono());
        estudiante.setDireccion(estudianteRQ.getDireccion().toUpperCase());
        estudiante.setCarrera(carrera.get());
        return this.estudianteRepository.save(estudiante);
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
}
