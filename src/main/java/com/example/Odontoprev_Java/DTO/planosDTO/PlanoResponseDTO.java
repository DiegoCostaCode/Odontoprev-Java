package com.example.Odontoprev_Java.DTO.planosDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PlanoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double preco,
        String ativo,
        LocalDateTime dataAtualizacao
){}
