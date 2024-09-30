package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClinicaServicoRequest(
        @NotNull
        Long idClinica,
        @NotNull
        List<Enum_tipo_servico> servicos
){
}
