package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.model.Clinica;
import com.example.Odontoprev_Java.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long>{

    Optional<Clinica> findByUsuario(Usuario usuario);

    Optional<Clinica> findByUsuarioId(Long id);
}
