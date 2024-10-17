package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_paises;
import com.example.Odontoprev_Java.Model.Endereco.Pais;

public record PaisResponseDTO
        (
                long id_pais,
                Enum_paises pais
        ) {
}
