package com.mibsonaprendizado.autorlivro.DTO;

import com.mibsonaprendizado.autorlivro.model.Autor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroResponseDTO {

    private Long id;

    private String autor;
    private String titulo;
    private int anoPublicacao;
    private String genero;
}
