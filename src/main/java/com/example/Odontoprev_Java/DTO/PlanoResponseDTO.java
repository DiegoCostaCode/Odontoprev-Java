package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_plano;
import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;

import java.util.List;

public record PlanoResponseDTO(
        Long id,
        List<Enum_tipo_plano> servicos
){
}
