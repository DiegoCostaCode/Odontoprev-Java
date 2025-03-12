package com.example.Odontoprev_Java.DTO.pacienteDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String dataNascimento;
}
