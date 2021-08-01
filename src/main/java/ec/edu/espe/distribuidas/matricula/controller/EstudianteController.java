package ec.edu.espe.distribuidas.matricula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.distribuidas.matricula.dto.EstudianteEditarRS;
import ec.edu.espe.distribuidas.matricula.dto.EstudianteRS;
import ec.edu.espe.distribuidas.matricula.model.Estudiante;
import ec.edu.espe.distribuidas.matricula.service.EstudianteService;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody EstudianteRS estudianteRs) {
        try {
            return ResponseEntity.ok(this.estudianteService.agregarEstudiante(estudianteRs));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<Estudiante> editarEstudiante(@PathVariable String usuario, @RequestBody EstudianteEditarRS estudianteEditarRs) {
        try {
            return ResponseEntity.ok(this.estudianteService.actualizarEstudiante(usuario, estudianteEditarRs));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
