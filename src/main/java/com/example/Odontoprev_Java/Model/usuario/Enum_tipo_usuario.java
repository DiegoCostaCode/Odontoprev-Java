package com.example.Odontoprev_Java.Model.usuario;

import lombok.Getter;

@Getter
public enum Enum_tipo_usuario {
    AUDITOR("Auditor"),
    CLINICA("Cliente"),
    PACIENTE("Paciente");

    private final String descricao;

    Enum_tipo_usuario(String descricao) {
        this.descricao = descricao;
    }
}
