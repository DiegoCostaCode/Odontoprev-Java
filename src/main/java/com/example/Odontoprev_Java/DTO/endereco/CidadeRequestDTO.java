package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Estado;

public record CidadeRequestDTO
        (
             String cidade,
             EstadoResponseDTO estado
        ) {
}
