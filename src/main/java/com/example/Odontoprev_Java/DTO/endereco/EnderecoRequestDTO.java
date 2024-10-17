package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Cidade;
import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_estado;
import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_paises;
import jakarta.validation.constraints.*;

public record EnderecoRequestDTO
        (
        @NotBlank
        @Size(min = 1, max = 100)
        String rua,

        @Size(max = 100)
        String complemento,

        @NotNull
        @Min(1)
        int numero,

        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}")
        String cep,

        @NotNull
        Cidade cidade
) {
}
