package com.example.Odontoprev_Java.model.usuario;

import lombok.Getter;

@Getter
public enum Enum_tipo_usuario {
    AUDITOR("auditor"),
    CLINICA("clinica"),
    PACIENTE("paciente");

    private final String descricao;

    Enum_tipo_usuario(String descricao) {
        this.descricao = descricao;
    }
}
