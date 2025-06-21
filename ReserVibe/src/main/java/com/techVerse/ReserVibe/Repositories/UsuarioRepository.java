package com.techVerse.ReserVibe.Repositories;

import com.techVerse.ReserVibe.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    UserDetails searchByEmail(String email);
}
