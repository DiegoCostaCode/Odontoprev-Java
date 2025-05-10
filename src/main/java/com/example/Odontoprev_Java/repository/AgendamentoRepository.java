package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.model.agendamento.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByClinicaIdOrderByDataAgendamentoDesc(Long clinicaId);
    List<Agendamento> findByPacienteIdOrderByDataAgendamentoDesc(Long pacienteId);
}
