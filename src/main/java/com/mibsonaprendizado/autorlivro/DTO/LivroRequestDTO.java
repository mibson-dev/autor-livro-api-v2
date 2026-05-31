package com.mibsonaprendizado.autorlivro.DTO;

import com.mibsonaprendizado.autorlivro.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LivroRequestDTO {

    @NotNull
    private Autor autor;

    @NotBlank
    private String titulo;

    @Positive
    private int anoPublicacao;

    @NotBlank
    private String genero;
}
