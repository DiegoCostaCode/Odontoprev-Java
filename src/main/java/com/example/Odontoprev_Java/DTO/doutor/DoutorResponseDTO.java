package com.example.Odontoprev_Java.DTO.doutor;

import com.example.Odontoprev_Java.Model.Endereco;

import java.time.LocalDate;
import java.util.Date;

public record DoutorResponseDTO(
        Long id,
        String nome,
        String cpf,
        String cro,
        String email,
        LocalDate dataNascimento,
        String telefone,
        Endereco endereco
        ) {
}
