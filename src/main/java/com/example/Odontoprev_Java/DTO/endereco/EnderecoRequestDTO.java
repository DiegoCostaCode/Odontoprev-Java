package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco;
import com.example.Odontoprev_Java.Model.Enums.Enum_estado;
import com.example.Odontoprev_Java.Model.Enums.Enum_paises;
import jakarta.validation.constraints.NotNull;

public record EnderecoRequestDTO
        (
                @NotNull
                String rua,
                @NotNull
                int numero,
                @NotNull
                String cep,
                @NotNull
                String bairro,
                @NotNull
                String cidade,
                @NotNull
                Enum_estado estado,
                @NotNull
                Enum_paises pais

        ) {
}
