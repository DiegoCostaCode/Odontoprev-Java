package com.example.Odontoprev_Java.DTO.atendimento;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record AtendimentoRequestDTO(
        @NotBlank(message = "Data de agendamento é obrigatória")
        @FutureOrPresent(message = "Data de agendamento deve ser no passado")
        LocalDateTime diaHoraAtendimento,

        @NotBlank
        @Size(min = 2, max = 100, message = "Descrição deve ter entre 2 e 100 caracteres")
        String descricao,

        @NotBlank(message = "Paciente é obrigatório")
        Paciente paciente,

        @NotNull(message = "Clinica é obrigatória")
        Clinica clinica,

        @NotNull(message = "Custo é obrigatório")
        @Positive(message = "Custo deve ser positivo")
        double custo,

        @NotBlank(message = "Um atendimento deve ter um procedimento!")
        Procedimento procedimento
) {
}