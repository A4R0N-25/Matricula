package ec.edu.espe.distribuidas.matricula.controller;

import ec.edu.espe.distribuidas.matricula.dto.LoginDto;
import ec.edu.espe.distribuidas.matricula.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        log.info("Usurio:{}",loginDto);
        boolean verificacion = this.loginService.authenticacion(loginDto);
        log.info("Validacion: {}",verificacion);
        if (verificacion){
            log.info("Logueo Correcto");
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
