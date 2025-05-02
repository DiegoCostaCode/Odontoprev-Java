package com.example.Odontoprev_Java.DTO.mistralDTO;

public record MistralPromptResponseDTO(
        Boolean fraude,
        String descricao_coerente,
        String resposta
) {
}
