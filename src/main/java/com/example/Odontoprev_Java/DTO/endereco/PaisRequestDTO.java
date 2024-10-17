package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_paises;
import jakarta.validation.constraints.NotNull;

public record PaisRequestDTO
        (
                @NotNull
                Enum_paises nome
        ) {
}
