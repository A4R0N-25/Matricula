package ec.edu.espe.distribuidas.matricula.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.distribuidas.matricula.dao.CarreraRepository;
import ec.edu.espe.distribuidas.matricula.dao.EstudianteRepository;
import ec.edu.espe.distribuidas.matricula.dto.EstudianteEditarRS;
import ec.edu.espe.distribuidas.matricula.dto.EstudianteRS;
import ec.edu.espe.distribuidas.matricula.exception.EntityNotFoundException;
import ec.edu.espe.distribuidas.matricula.model.Carrera;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    public Estudiante agregarEstudiante(EstudianteRS estudianteRs) {
        List<Estudiante> estudiantes = this.estudianteRepository
            .findByCorreoOrIdentificacion(estudianteRs.getUsuario() + "@espe.edu.ec", estudianteRs.getIdentificacion());
        if(!estudiantes.isEmpty()) {
            throw new EntityExistsException("Ya existe un usuario con esta cedula o nombre de usuario");
        }
        Optional<Carrera> carrera = this.carreraRepository.findById(estudianteRs.getCarrera());
        if(carrera.isEmpty()) {
            throw new EntityNotFoundException("No existe la carrera con id: " + estudianteRs.getCarrera());
        }
        Estudiante estudiante = Estudiante.builder().apellido(estudianteRs.getApellido())
                .carrera(carrera.get())
                .contraseña(estudianteRs.getContraseña())
                .correo(estudianteRs.getUsuario() + "@espe.edu.ec")
                .direccion(estudianteRs.getDireccion())
                .genero(estudianteRs.getGenero())
                .identificacion(estudianteRs.getIdentificacion())
                .nombre(estudianteRs.getNombre())
                .telefono(estudianteRs.getTelefono())
                .tipoIdentificacion(estudianteRs.getTipoIdentificacion()).build();
        return this.estudianteRepository.save(estudiante);
    }

    public Estudiante actualizarEstudiante(String usuario, EstudianteEditarRS estudianteRs) {
        Optional<Estudiante> estudianteOptional = this.estudianteRepository.findByCorreo(usuario + "@espe.edu.ec");
        if(estudianteOptional.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el estudiante con el usuario: " + usuario);
        }
        Estudiante estudiante2 = Estudiante.builder().apellido(estudianteRs.getApellido())
            .nombre(estudianteRs.getNombre())
            .genero(estudianteRs.getGenero())
            .telefono(estudianteRs.getTelefono())
            .direccion(estudianteRs.getDireccion()).build();
        return this.estudianteRepository.save(estudiante2);
    }
  
  public Estudiante obtenerEstudanterPorCorreo(String correo){
        Optional<Estudiante> estudianteOpt = this.estudianteRepository.findByCorreo(correo);
        
        if(estudianteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el estudiante con el correo: " + correo);
        }
        
        return estudianteOpt.get();
    }
}
