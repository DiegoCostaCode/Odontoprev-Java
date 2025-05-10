package com.example.Odontoprev_Java.model.procedimento;

import lombok.Getter;

@Getter
public enum Enum_procedimento {
    LIMPEZA("Limpeza dentária"),
    RESTAURACAO("Restauração dentária"),
    CANAL("Tratamento de canal"),
    ORTODONTIA("Tratamento ortodôntico"),
    IMPLANTE("Implante dentário"),
    EXTRAÇÃO("Extração dentária"),
    CLAREAMENTO("Clareamento dental"),
    PROTESE("Prótese dentária"),
    APARELHO("Aparelho ortodôntico"),
    RADIOGRAFIA("Radiografia dentária");

    private final String descricao;

    Enum_procedimento(String descricao) {
        this.descricao = descricao;
    }

}