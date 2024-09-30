package com.example.Odontoprev_Java.DTO;

import jakarta.validation.constraints.NotNull;

public record ClinicaDoutorRequest(
        @NotNull(message = "O id da clínica é obrigatório")
        Long idClinica,
        @NotNull(message = "O id do doutor é obrigatório")
        Long idDoutor
) {
}
