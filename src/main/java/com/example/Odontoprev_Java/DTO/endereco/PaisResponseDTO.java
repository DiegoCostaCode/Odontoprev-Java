package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Pais;

public record PaisResponseDTO
        (
                Long id_pais,
                Pais pais
        ) {
}
