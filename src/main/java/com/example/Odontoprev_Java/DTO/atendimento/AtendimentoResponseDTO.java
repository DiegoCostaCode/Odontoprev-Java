package com.example.Odontoprev_Java.DTO.atendimento;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;

import java.time.LocalDate;

public record AtendimentoResponseDTO(
        Long id,
        LocalDate data,
        String descricao,
        Paciente paciente,
        Clinica clinica,
        double custo,
        Procedimento procedimento
) {
}