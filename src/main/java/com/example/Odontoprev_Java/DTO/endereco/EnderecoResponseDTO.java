package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Bairro;

public record EnderecoResponseDTO
        (
                Long id,
                String rua,
                int numero,
                String cep,
                Bairro bairro
        ){
}
