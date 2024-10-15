package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_estado;

public record EstadoRequestDTO
        (
        Enum_estado nome,
        PaisRequestDTO pais
        ) {
}
