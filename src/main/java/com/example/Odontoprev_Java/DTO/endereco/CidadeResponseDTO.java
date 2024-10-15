package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Estado;

public record CidadeResponseDTO
        (
        Long id_cidade,
        Estado estado
        ) {
}
