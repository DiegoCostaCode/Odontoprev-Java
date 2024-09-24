package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Endereco;

import java.util.Date;

public record DoutorResponseDTO(
        Long id,
        String nome,
        String cpf,
        String cro,
        String email,
        Date dataNascimento,
        String telefone,
        Endereco endereco
        ) {
}
