package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Plano;
import jakarta.validation.constraints.NotNull;

public record CarterinhaRequestDTO
        (
                @NotNull
                Long usuarioId,

                @NotNull
                Plano planoId
        ) {



}
