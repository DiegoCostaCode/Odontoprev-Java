package com.example.Odontoprev_Java.DTO.endereco;

import jakarta.validation.constraints.*;

public record CidadeRequestDTO
        (
                @NotNull
                @Size(min = 1, max = 50)
                String nome,

                @NotNull
                EstadoRequestDTO estado
        ) {
}