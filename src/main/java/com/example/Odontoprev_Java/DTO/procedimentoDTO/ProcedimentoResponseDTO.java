package com.example.Odontoprev_Java.DTO.procedimentoDTO;

import java.time.LocalDateTime;

public record ProcedimentoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Double valorCobertura,
        LocalDateTime dataAtualizacao,
        String status
) {
}
