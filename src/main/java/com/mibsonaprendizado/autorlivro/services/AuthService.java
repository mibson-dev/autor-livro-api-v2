package com.mibsonaprendizado.autorlivro.services;

import com.mibsonaprendizado.autorlivro.DTO.UsuarioRequestDTO;
import com.mibsonaprendizado.autorlivro.model.Usuario;
import com.mibsonaprendizado.autorlivro.repositories.UsuarioRepository;
import com.mibsonaprendizado.autorlivro.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthService(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public void registrar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(null, dto.email(), passwordEncoder.encode(dto.senha()), "ROLE_USER");
        usuarioRepository.save(usuario);
    }

    public String login(UsuarioRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );
        return jwtService.gerarToken(dto.email());
    }
}