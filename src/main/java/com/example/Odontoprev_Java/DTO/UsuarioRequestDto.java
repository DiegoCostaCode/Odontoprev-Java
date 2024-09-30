package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Endereco;
import jakarta.validation.constraints.*;
import org.aspectj.weaver.ast.Not;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record UsuarioRequestDto(
        @NotNull
        String nome

){ }
