package com.techVerse.ReserVibe.Services;

import com.techVerse.ReserVibe.Security.SecurityConfig;
import com.techVerse.ReserVibe.Dtos.UsuarioDto;
import com.techVerse.ReserVibe.Models.Usuario;
import com.techVerse.ReserVibe.Repositories.UsuarioRepository;
import com.techVerse.ReserVibe.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioDto criarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        usuario.setRole(usuarioDto.getRole());
        usuario = usuarioRepository.save(usuario);

        return new UsuarioDto(usuario);
    }

    public String login(String usuario, String senha) {
        Usuario user = usuarioRepository.findByEmail(usuario);
        if (user == null || !passwordEncoder.matches(senha, user.getSenha())) return null;
        
        return tokenService.generateToken(user.getEmail());
    }
}
