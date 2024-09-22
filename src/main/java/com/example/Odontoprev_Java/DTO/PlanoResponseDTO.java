package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Servicos;

import java.util.List;

public record PlanoResponseDTO(
        Long id,
        String nome,
        List<Servicos> servicos,
        Double preco
){
}
