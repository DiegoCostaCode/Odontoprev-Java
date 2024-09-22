package com.example.Odontoprev_Java.Model.Enums;

public enum Enum_paises {
    BRASIL("Brasil");

    private String descricao;

    Enum_paises(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
