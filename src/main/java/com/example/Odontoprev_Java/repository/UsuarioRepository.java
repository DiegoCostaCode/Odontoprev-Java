package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.Model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
