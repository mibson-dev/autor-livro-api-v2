package com.mibsonaprendizado.autorlivro.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericExcepion (Exception e) {
        ErroResponse erro = new ErroResponse(500, "Erro interno no servidor.", LocalDateTime.now());
        return ResponseEntity.status(500).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> fieldNotValid (MethodArgumentNotValidException e) {
        String mensagem = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ErroResponse erro = new ErroResponse(400, mensagem, LocalDateTime.now());
        return ResponseEntity.status(400).body(erro);
    }

    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> autorNaoEcontrado (AutorNaoEncontradoException e) {
        ErroResponse erro = new ErroResponse(404, "Autor não encontrado.", LocalDateTime.now());
        return ResponseEntity.status(404).body(erro);
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> livroNaoEncontrado (LivroNaoEncontradoException e) {
        ErroResponse erro = new ErroResponse(404, "Livro não encontrado.", LocalDateTime.now());
        return ResponseEntity.status(404).body(erro);
    }
}
