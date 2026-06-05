package com.mibsonaprendizado.autorlivro.controller;

import com.mibsonaprendizado.autorlivro.DTO.UsuarioRequestDTO;
import com.mibsonaprendizado.autorlivro.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody @Valid UsuarioRequestDTO dto) {
        authService.registrar(dto);
        return ResponseEntity.status(201).body("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UsuarioRequestDTO dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(token);
    }
}