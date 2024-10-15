package com.example.Odontoprev_Java.DTO.consulta;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Paciente;

import java.time.LocalDate;

public record ConsultaResponseDTO(
        Long id,
        LocalDate agendamento,
        Enum_tipo_servico servico,
        Paciente paciente,
        Doutor doutor,
        Clinica clinica,
        String observacoes
) {
}