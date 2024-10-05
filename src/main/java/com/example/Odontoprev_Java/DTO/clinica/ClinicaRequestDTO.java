package com.example.Odontoprev_Java.DTO.clinica;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClinicaRequestDTO
        (
                @NotBlank
                String razaoSocial,
                @NotNull
                List<Enum_tipo_servico> servicos,
                @NotBlank
                String emailRepresentante
        ) {

}
