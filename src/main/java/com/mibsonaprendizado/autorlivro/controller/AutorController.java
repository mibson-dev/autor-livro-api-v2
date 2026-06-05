package com.mibsonaprendizado.autorlivro.controller;

import com.mibsonaprendizado.autorlivro.DTO.AutorRequestDTO;
import com.mibsonaprendizado.autorlivro.DTO.AutorResponseDTO;
import com.mibsonaprendizado.autorlivro.services.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public Page<AutorResponseDTO> listar (@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
        return autorService.listarAutores(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok().body(autorService.buscarAutorPorId(id));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> cadastrar (@RequestBody @Valid AutorRequestDTO autorRequestDTO) {
        return ResponseEntity.ok().body(autorService.cadastrarAutor(autorRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> deletarPorId (@PathVariable Long id) {
        autorService.deletarAutorPorId(id);
        return ResponseEntity.status(204).build();
    }
}
