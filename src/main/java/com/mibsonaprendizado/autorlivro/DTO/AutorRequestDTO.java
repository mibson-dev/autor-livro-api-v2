package com.mibsonaprendizado.autorlivro.DTO;

import jakarta.validation.constraints.NotBlank;

public record AutorRequestDTO (@NotBlank String nome, @NotBlank String nacionalidade) {

}
