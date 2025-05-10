package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.model.Paciente;
import com.example.Odontoprev_Java.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByUsuario(Usuario usuario);

    Optional<Paciente> findByUsuarioId(Long id);
}
