package com.example.Odontoprev_Java.DTO.plano;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_plano;

public record PlanoResponseDTO(
        Long id,
        Enum_tipo_plano tipo_plano
){
}
