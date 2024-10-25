package com.example.Odontoprev_Java.DTO.atendimento;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtendimentoResponseDTO(
        Long id_atendimento,
        LocalDateTime diaHoraAtendimento,
        String descricao,
        Paciente paciente,
        Clinica clinica,
        double custo,
        Procedimento procedimento
) {
}