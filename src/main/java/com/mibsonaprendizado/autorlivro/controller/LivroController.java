package com.mibsonaprendizado.autorlivro.controller;

import com.mibsonaprendizado.autorlivro.DTO.LivroRequestDTO;
import com.mibsonaprendizado.autorlivro.DTO.LivroResponseDTO;
import com.mibsonaprendizado.autorlivro.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<LivroResponseDTO> listar () {
        return livroService.listarLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId (@PathVariable Long id) {
        return ResponseEntity.ok().body(livroService.buscarLivroPorId(id));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastrar (@RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        return ResponseEntity.ok().body(livroService.cadastrarLivro(livroRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> deletarPorId (@PathVariable Long id) {
        livroService.deletarLivroPorId(id);
        return ResponseEntity.status(204).build();
    }
}
