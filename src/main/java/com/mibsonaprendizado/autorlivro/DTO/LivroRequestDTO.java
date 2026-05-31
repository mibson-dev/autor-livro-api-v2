package com.mibsonaprendizado.autorlivro.DTO;

import com.mibsonaprendizado.autorlivro.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record LivroRequestDTO (@NotNull Autor autor, @NotBlank String titulo, @Positive int anoPublicacao, @NotBlank String genero) {

}
