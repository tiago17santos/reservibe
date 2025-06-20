package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Configs.SecurityConfig;
import com.techVerse.ReserVibe.Dtos.UsuarioDto;
import com.techVerse.ReserVibe.Models.Usuario;
import com.techVerse.ReserVibe.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public UsuarioDto criarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(securityConfig.passwordEncoder().encode(usuarioDto.getSenha()));
        usuario.setRole(usuarioDto.getRole());
        usuario = usuarioRepository.save(usuario);

        return new UsuarioDto(usuario);
    }

    public UsuarioDto login(String usuario, String senha) {
        Usuario user = usuarioRepository.findByEmail(usuario);

        if (user == null) return null;

        if (securityConfig.passwordEncoder().matches(senha, user.getSenha())) {
            System.out.println("Login correto");
            return new UsuarioDto(user);
        }else {
            System.out.println("Login incorreto");
            return null;
        }

    }
}
