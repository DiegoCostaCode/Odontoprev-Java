package com.example.Odontoprev_Java.DTO.plano;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PlanoRequestDTO (

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(min = 10, max = 500, message = "Descrição deve ter entre 10 e 500 caracteres")
        String descricao,

        @Positive(message = "Preço deve ser positivo")
        double preco,

        @NotNull(message = "Ativo é obrigatório")
        boolean ativo

    ){
}
