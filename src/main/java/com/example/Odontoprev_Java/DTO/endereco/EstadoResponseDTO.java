package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_estado;
import com.example.Odontoprev_Java.Model.Endereco.Pais;

public record EstadoResponseDTO
        (
                Long id_estado,
                Enum_estado nome,
                Pais pais
        ){
}
