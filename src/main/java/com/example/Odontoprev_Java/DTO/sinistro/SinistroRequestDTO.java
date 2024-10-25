package com.example.Odontoprev_Java.DTO.sinistro;

import com.example.Odontoprev_Java.Model.Atendimento;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SinistroRequestDTO
        (

                @NotBlank(message = "A descrição não pode estar em branco")
                String descricao,

                @Positive(message = "O custo excedente não pode ser negativo")
                double custo_excedente,

                @NotNull(message = "O atendimento não pode ser nulo")
                Atendimento atendimento_id
        ) {
}
