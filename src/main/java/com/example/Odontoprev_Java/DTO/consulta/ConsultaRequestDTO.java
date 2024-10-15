package com.example.Odontoprev_Java.DTO.consulta;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ConsultaRequestDTO(
        @NotNull
        @Past(message = "A data de agendamento deve ser no passado.")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate agendamento,

        @NotNull
        Enum_tipo_servico servico,

        @NotNull
        Long usuarioId,

        Doutor doutorId,

        @NotNull
        Clinica clinicaId,

        String observacoes
) {
}