package com.mibsonaprendizado.autorlivro.DTO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank String email,
        @NotBlank String senha
) {}