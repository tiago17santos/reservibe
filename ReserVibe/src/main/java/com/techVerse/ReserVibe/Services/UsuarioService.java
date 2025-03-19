package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Dtos.UsuarioDto;
import com.techVerse.ReserVibe.Models.Usuario;
import com.techVerse.ReserVibe.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDto criarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setRole(usuarioDto.getRole());
        usuario = usuarioRepository.save(usuario);

        return new UsuarioDto(usuario);
    }
}
