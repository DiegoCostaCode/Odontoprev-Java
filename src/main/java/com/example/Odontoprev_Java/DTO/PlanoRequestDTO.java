package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_plano;
import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PlanoRequestDTO (

    @NotBlank
     List<Enum_tipo_plano> servicos

    ){
}
