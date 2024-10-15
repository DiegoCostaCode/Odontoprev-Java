package com.example.Odontoprev_Java.DTO.carteirinha;

import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Plano;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record CarterinhaRequestDTO
        (
                @NotNull(message = "Data de emissão não pode ser nula")
                @PastOrPresent(message = "Data de emissão deve ser no passado ou presente")
                Date emissao,

                @NotNull(message = "Data de validade não pode ser nula")
                @Future(message = "Data de validade deve ser no futuro")
                Date validade,

                @NotNull(message = "Plano não pode ser nulo")
                Plano planoId,

                @NotNull(message = "Paciente não pode ser nulo")
                Paciente pacienteId
        ) {



}
