package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Atendimento, Long> {
}
