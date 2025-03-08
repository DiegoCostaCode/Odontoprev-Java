package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.Model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long>{
}
