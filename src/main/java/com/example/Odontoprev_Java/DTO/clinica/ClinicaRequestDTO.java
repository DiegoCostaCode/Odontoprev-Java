package com.example.Odontoprev_Java.DTO.clinica;

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
