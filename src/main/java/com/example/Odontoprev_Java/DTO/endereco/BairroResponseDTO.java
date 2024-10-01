package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Cidade;

public record BairroResponseDTO
        (
                Long id,
                String bairro,
                Cidade cidade
        ) {
}
