package com.example.Odontoprev_Java.DTO.endereco;

import com.example.Odontoprev_Java.Model.Endereco.Cidade;
import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_estado;
import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_paises;
import com.example.Odontoprev_Java.Model.Endereco.Estado;
import com.example.Odontoprev_Java.Model.Endereco.Pais;

public record EnderecoResponseDTO
        (
                Long id,
                String rua,
                int numero,
                String cep,
                Cidade cidade,
                Estado estado,
                Pais pais
        ) {
}
