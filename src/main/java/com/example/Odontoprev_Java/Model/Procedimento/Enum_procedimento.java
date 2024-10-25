package com.example.Odontoprev_Java.Model.Procedimento;

import lombok.Getter;

@Getter
public enum Enum_procedimento {
    LIMPEZA("Limpeza dentária", 100.0),
    RESTAURACAO("Restauração dentária", 200.0),
    CANAL("Tratamento de canal", 500.0),
    ORTODONTIA("Tratamento ortodôntico", 1500.0),
    IMPLANTE("Implante dentário", 3000.0),
    EXTRAÇÃO("Extração dentária", 250.0),
    CLAREAMENTO("Clareamento dental", 800.0),
    PROTESE("Prótese dentária", 1200.0),
    APARELHO("Aparelho ortodôntico", 2000.0),
    RADIOGRAFIA("Radiografia dentária", 150.0);

    private final String descricao;
    private final double orcamentoMedio;

    Enum_procedimento(String descricao, double orcamentoMedio) {
        this.descricao = descricao;
        this.orcamentoMedio = orcamentoMedio;
    }

}