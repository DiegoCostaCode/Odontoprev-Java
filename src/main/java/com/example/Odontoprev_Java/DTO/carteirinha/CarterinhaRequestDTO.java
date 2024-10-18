package com.example.Odontoprev_Java.DTO.carteirinha;

import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Plano;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record CarterinhaRequestDTO
        (
                @NotNull(message = "Plano não pode ser nulo")
                Plano planoId,

                @NotNull(message = "Paciente não pode ser nulo")
                Paciente pacienteId
        ) {

}
