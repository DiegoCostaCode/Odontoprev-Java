package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Enums.Enum_estado;
import com.example.Odontoprev_Java.Model.Pais;

public record EstadoResponseDTO
        (
              Long id,
              Enum_estado estado,
              PaisResponseDTO pais
        ) {
}
