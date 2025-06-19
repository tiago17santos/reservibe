package com.techVerse.ReserVibe.Controllers;

import com.techVerse.ReserVibe.Dtos.UsuarioDto;
import com.techVerse.ReserVibe.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        usuarioDto = usuarioService.criarUsuario(usuarioDto);

        return ResponseEntity.ok().body(usuarioDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto usuario) {
        usuario = usuarioService.login(usuario.getEmail(), usuario.getSenha());

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
