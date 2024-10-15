package com.example.Odontoprev_Java.DTO.plano;

public record PlanoResponseDTO(
        Long id_plano,
        String nome,
        String descricao,
        double preco
){
}
