package demo.kun.uz.controller;

import demo.kun.uz.dto.AuthDTO;
import demo.kun.uz.dto.ProfileDTO;
import demo.kun.uz.dto.RegistrationDTO;
import demo.kun.uz.service.AutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutController {

    @Autowired
    private AutService authService;


    @PostMapping("/registration")
    public ResponseEntity<String> registrations(@RequestBody RegistrationDTO dto){
        return ResponseEntity.ok(authService.registration(dto));
    }

    @GetMapping("/registration/confirm/{id}")
    public ResponseEntity<String> registration(@PathVariable Long id){
        return ResponseEntity.ok(authService.registrationConfirm(id));
    }
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody   AuthDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}
