package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.Usuario;
import jakarta.persistence.Column;
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
