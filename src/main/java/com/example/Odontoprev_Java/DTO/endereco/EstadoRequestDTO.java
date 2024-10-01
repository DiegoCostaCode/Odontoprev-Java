package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Enums.Enum_estado;
import com.example.Odontoprev_Java.Model.Pais;

public record EstadoRequestDTO
        (
                Enum_estado estado,
                Pais pais
        ){
}
