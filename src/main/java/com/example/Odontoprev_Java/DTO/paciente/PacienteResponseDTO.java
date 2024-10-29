package com.example.Odontoprev_Java.DTO.paciente;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;

import java.time.LocalDate;
import java.util.Date;

public record PacienteResponseDTO(
        Long id,
        String nome,
        Date dataNascimento,
        String  cpf,
        String email,
        String telefone,
        Endereco endereco
) {


}
