package com.example.Odontoprev_Java.DTO.clinica;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ClinicaRequestDTO
        (
                @NotBlank(message = "Razão Social é obrigatória")
                String razaoSocial,

                @NotNull(message = "Descrição não pode ser nula")
                String descricao,

                @NotNull(message = "CNPJ não pode ser nulo")
                @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos")
                String CNPJ,

                @NotBlank(message = "Email do representante é obrigatório")
                @Email(message = "Email do representante deve ser válido")
                String emailRepresentante
        ) {

}
