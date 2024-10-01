package com.example.Odontoprev_Java.DTO.usuario;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Consulta;
import com.example.Odontoprev_Java.Model.Endereco;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public record UsuarioResponseDto(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String  cpf,
        String email,
        String telefone,
        Carteirinha carteirinha,
        Endereco endereco,
        List<Consulta> consultas
) {


}