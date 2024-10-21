package com.example.Odontoprev_Java.DTO.clinicaDoutor;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDate;

public record ClinicaDoutorRequestDTO(
        @NotNull(message = "ID da clínica é obrigatório")
        Clinica clinicaId,

        @NotNull(message = "ID do doutor é obrigatório")
        Doutor doutorId,

        @NotNull(message = "Data de relacionamento é obrigatória")
        @PastOrPresent
        LocalDate dataRelacionamento,

        @FutureOrPresent(message = "Data de fim de relacionamento deve ser no presente ou futuro")
        LocalDate dataFimRelacionamento
) {}
