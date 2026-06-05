package com.mibsonaprendizado.autorlivro.repositories;

import com.mibsonaprendizado.autorlivro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
