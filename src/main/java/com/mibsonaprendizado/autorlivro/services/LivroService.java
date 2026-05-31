package com.mibsonaprendizado.autorlivro.services;

import com.mibsonaprendizado.autorlivro.DTO.LivroRequestDTO;
import com.mibsonaprendizado.autorlivro.DTO.LivroResponseDTO;
import com.mibsonaprendizado.autorlivro.exceptions.AutorNaoEncontradoException;
import com.mibsonaprendizado.autorlivro.exceptions.LivroNaoEncontradoException;
import com.mibsonaprendizado.autorlivro.model.Autor;
import com.mibsonaprendizado.autorlivro.model.Livro;
import com.mibsonaprendizado.autorlivro.repositories.AutorRepository;
import com.mibsonaprendizado.autorlivro.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public List<LivroResponseDTO> listarLivros () {

        List<Livro> livros = livroRepository.findAll();

        List<LivroResponseDTO> livroResponseDTOS = new ArrayList<>();

        for (Livro livro : livros) {

            LivroResponseDTO livroResponseDTO = new LivroResponseDTO(
                    livro.getAutor().getNome(),
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAnoPublicacao(),
                    livro.getGenero()
            );

            livroResponseDTOS.add(livroResponseDTO);
        }
        return livroResponseDTOS;
    }

    public LivroResponseDTO buscarLivroPorId (Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO(
                livro.getAutor().getNome(),
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoPublicacao(),
                livro.getGenero()
        );

        return livroResponseDTO;
    }

    public LivroResponseDTO cadastrarLivro (LivroRequestDTO livroRequestDTO) {
        Autor autorEncontrado = autorRepository.findById(livroRequestDTO.autor().getId())
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado."));

        Livro livro = new Livro();

        livro.setAutor(autorEncontrado);
        livro.setTitulo(livroRequestDTO.titulo());
        livro.setAnoPublicacao(livroRequestDTO.anoPublicacao());
        livro.setGenero(livroRequestDTO.genero());

        Livro livroSalvo = livroRepository.save(livro);

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO(
                livroSalvo.getAutor().getNome(),
                livroSalvo.getId(),
                livroSalvo.getTitulo(),
                livroSalvo.getAnoPublicacao(),
                livroSalvo.getGenero());

        return livroResponseDTO;
    }

    public void deletarLivroPorId (Long id) {
        livroRepository.deleteById(id);
    }
}
