package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Enums.Enum_estado;
import com.example.Odontoprev_Java.Model.Enums.Enum_paises;

public record EnderecoResponseDTO
        (
                Long id,
                String rua,
                int numero,
                String cep,
                String bairro,
                String cidade,
                Enum_estado estado,
                Enum_paises pais
        ) {
}
