package com.mibsonaprendizado.autorlivro.services;

import com.mibsonaprendizado.autorlivro.DTO.AutorRequestDTO;
import com.mibsonaprendizado.autorlivro.DTO.AutorResponseDTO;
import com.mibsonaprendizado.autorlivro.exceptions.AutorNaoEncontradoException;
import com.mibsonaprendizado.autorlivro.model.Autor;
import com.mibsonaprendizado.autorlivro.repositories.AutorRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorResponseDTO> listarAutores () {
        List<Autor> autores = autorRepository.findAll();

        List<AutorResponseDTO> autorResponseDTOS = new ArrayList<>();

        for (Autor autor : autores) {
            AutorResponseDTO autorResponseDTO = new AutorResponseDTO();

            autorResponseDTO.setId(autor.getId());
            autorResponseDTO.setNome(autor.getNome());
            autorResponseDTO.setNacionalidade(autor.getNacionalidade());

            autorResponseDTOS.add(autorResponseDTO);
        }
        return autorResponseDTOS;
    }

    public AutorResponseDTO buscarAutorPorId (Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado."));

        AutorResponseDTO autorResponseDTO = new AutorResponseDTO();

        autorResponseDTO.setId(autor.getId());
        autorResponseDTO.setNome(autor.getNome());
        autorResponseDTO.setNacionalidade(autor.getNacionalidade());

        return autorResponseDTO;
    }

    public AutorResponseDTO cadastrarAutor (AutorRequestDTO autorRequestDTO) {
        Autor autor = new Autor();

        autor.setNome(autorRequestDTO.getNome());
        autor.setNacionalidade(autorRequestDTO.getNacionalidade());

        Autor autorSalvo = autorRepository.save(autor);

        AutorResponseDTO autorResponseDTO = new AutorResponseDTO();

        autorResponseDTO.setId(autorSalvo.getId());
        autorResponseDTO.setNome(autorSalvo.getNome());
        autorResponseDTO.setNacionalidade(autorSalvo.getNacionalidade());

        return autorResponseDTO;
    }

    public void deletarAutorPorId (Long id) {
        autorRepository.deleteById(id);
    }
}
