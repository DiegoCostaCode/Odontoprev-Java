package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Estado;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CidadeRequestDTO
        (
        @NotBlank
        @Min(1)
        @Max(30)
        String nome,

        @NotBlank
        EstadoRequestDTO estado
        ){
}
