package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Consulta;
import com.example.Odontoprev_Java.Model.Endereco;

import java.util.List;

public record UsuarioResponseDto(
        Long id,
        String nome,
        String  cpf,
        String email,
        String telefone,
        Carteirinha carteirinha,
        Endereco endereco,
        List<Consulta> consultas
) {


}
