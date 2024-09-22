package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Endereco;

public record UsuarioResponseDto(
        Long id,
        String nome,
        String  cpf,
        String email,
        String telefone,
        Carteirinha carteirinha,
        Endereco endereco
) {


}
