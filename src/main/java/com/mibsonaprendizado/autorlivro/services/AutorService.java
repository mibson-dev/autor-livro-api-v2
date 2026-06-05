package com.mibsonaprendizado.autorlivro.services;

import com.mibsonaprendizado.autorlivro.DTO.AutorRequestDTO;
import com.mibsonaprendizado.autorlivro.DTO.AutorResponseDTO;
import com.mibsonaprendizado.autorlivro.exceptions.AutorNaoEncontradoException;
import com.mibsonaprendizado.autorlivro.model.Autor;
import com.mibsonaprendizado.autorlivro.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Page<AutorResponseDTO> listarAutores (Pageable pageable) {
        return autorRepository.findAll(pageable).map(autor -> new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade()
        ));
    }

    public AutorResponseDTO buscarAutorPorId (Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado."));

        return new AutorResponseDTO(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade()
        );
    }

    public AutorResponseDTO cadastrarAutor (AutorRequestDTO autorRequestDTO) {
        Autor autor = new Autor();

        autor.setNome(autorRequestDTO.nome());
        autor.setNacionalidade(autorRequestDTO.nacionalidade());

        Autor autorSalvo = autorRepository.save(autor);

        return new AutorResponseDTO(
                autorSalvo.getId(),
                autorSalvo.getNome(),
                autorSalvo.getNacionalidade()
        );
    }

    public void deletarAutorPorId (Long id) {
        autorRepository.deleteById(id);
    }
}
