package com.example.Odontoprev_Java.DTO.sinistro;

import com.example.Odontoprev_Java.Model.Atendimento;

import java.time.LocalDate;

public record SinistroResponseDTO
        (
                Long id,
                LocalDate data_sinistro,
                String descricao,
                double custo_excedente,
                Atendimento atendimento_id
        ){
}
