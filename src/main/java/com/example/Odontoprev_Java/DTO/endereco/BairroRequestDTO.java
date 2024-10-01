package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Cidade;

public record BairroRequestDTO
        (
                String bairro,
                Cidade cidade
        ) {
}
