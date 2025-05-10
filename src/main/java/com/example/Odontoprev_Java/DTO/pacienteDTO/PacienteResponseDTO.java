package com.example.Odontoprev_Java.DTO.pacienteDTO;

import com.example.Odontoprev_Java.model.Plano;

import java.time.LocalDate;

public record PacienteResponseDTO
        (
                Long id,
                String nome,
                String cpf,
                LocalDate dataNascimento,
                String email,
                String telefone,
                Plano plano,
                Long usuario_id
        ){
}
