package com.example.Odontoprev_Java.DTO.atendimento;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

public record AtendimentoResponseDTO(
        Long id,
        Date data,
        String descricao,
        Paciente paciente,
        Clinica clinica,
        double custo
) {
}