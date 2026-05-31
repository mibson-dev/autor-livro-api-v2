package com.mibsonaprendizado.autorlivro.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorResponseDTO {

    private Long id;
    private String nome;
    private String nacionalidade;
}
