package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Enums.Enum_paises;

public record PaisResponseDTO
        (

                Long id,
                Enum_paises pais
        ) {
}
