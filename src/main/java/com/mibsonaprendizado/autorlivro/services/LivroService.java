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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Page<LivroResponseDTO> listarLivros (Pageable pageable) {

        return livroRepository.findAll(pageable)
                .map(livro -> new LivroResponseDTO(
                        livro.getAutor().getNome(),
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getAnoPublicacao(),
                        livro.getGenero()
                ));
    }

    public LivroResponseDTO buscarLivroPorId (Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado."));

        return new LivroResponseDTO(
                livro.getAutor().getNome(),
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoPublicacao(),
                livro.getGenero()
        );
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

        return new LivroResponseDTO(
                livroSalvo.getAutor().getNome(),
                livroSalvo.getId(),
                livroSalvo.getTitulo(),
                livroSalvo.getAnoPublicacao(),
                livroSalvo.getGenero());
    }

    public void deletarLivroPorId (Long id) {
        livroRepository.deleteById(id);
    }
}
