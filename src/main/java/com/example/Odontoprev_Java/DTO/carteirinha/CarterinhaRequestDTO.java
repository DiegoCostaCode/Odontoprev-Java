package com.example.Odontoprev_Java.DTO.carteirinha;

import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CarterinhaRequestDTO
        (

                @NotNull
                Date emissao,
                @NotNull
                Date validade,
                @NotNull
                Plano planoId,
                @NotNull
                Usuario usuarioId
        ) {



}
