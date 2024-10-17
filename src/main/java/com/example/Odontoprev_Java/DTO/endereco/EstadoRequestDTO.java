package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_estado;
import jakarta.validation.constraints.*;

public record EstadoRequestDTO
        (
                @NotNull
                Enum_estado nome,

                @NotNull
                PaisRequestDTO pais
        ) {
}