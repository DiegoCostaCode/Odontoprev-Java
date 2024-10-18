package com.example.Odontoprev_Java.DTO.paciente;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;

import java.time.LocalDate;

public record PacienteResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String  cpf,
        String email,
        String telefone,
        Endereco endereco
) {


}
