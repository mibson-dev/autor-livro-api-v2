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
            LivroResponseDTO livroResponseDTO = new LivroResponseDTO();

            livroResponseDTO.setAutor(livro.getAutor().getNome());
            livroResponseDTO.setId(livro.getId());
            livroResponseDTO.setTitulo(livro.getTitulo());
            livroResponseDTO.setAnoPublicacao(livro.getAnoPublicacao());
            livroResponseDTO.setGenero(livro.getGenero());

            livroResponseDTOS.add(livroResponseDTO);
        }
        return livroResponseDTOS;
    }

    public LivroResponseDTO buscarLivroPorId (Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO();

        livroResponseDTO.setAutor(livro.getAutor().getNome());
        livroResponseDTO.setId(livro.getId());
        livroResponseDTO.setTitulo(livro.getTitulo());
        livroResponseDTO.setAnoPublicacao(livro.getAnoPublicacao());
        livroResponseDTO.setGenero(livro.getGenero());

        return livroResponseDTO;
    }

    public LivroResponseDTO cadastrarLivro (LivroRequestDTO livroRequestDTO) {
        Autor autorEncontrado = autorRepository.findById(livroRequestDTO.getAutor().getId())
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado."));

        Livro livro = new Livro();

        livro.setAutor(autorEncontrado);
        livro.setTitulo(livroRequestDTO.getTitulo());
        livro.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());
        livro.setGenero(livroRequestDTO.getGenero());

        Livro livroSalvo = livroRepository.save(livro);

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO();

        livroResponseDTO.setAutor(livroSalvo.getAutor().getNome());
        livroResponseDTO.setId(livroSalvo.getId());
        livroResponseDTO.setTitulo(livroSalvo.getTitulo());
        livroResponseDTO.setAnoPublicacao(livroSalvo.getAnoPublicacao());
        livroResponseDTO.setGenero(livroSalvo.getGenero());

        return livroResponseDTO;
    }

    public void deletarLivroPorId (Long id) {
        livroRepository.deleteById(id);
    }
}
