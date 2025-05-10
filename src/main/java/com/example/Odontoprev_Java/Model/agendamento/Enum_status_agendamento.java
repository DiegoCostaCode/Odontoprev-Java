package com.example.Odontoprev_Java.model.agendamento;

import lombok.Getter;

@Getter
public enum Enum_status_agendamento {
    MARCADA("Consulta marcada"),
    FINALIZADA("Consulta finalizada");

    private final String descricao;

    Enum_status_agendamento(String descricao) {
        this.descricao = descricao;
    }

}