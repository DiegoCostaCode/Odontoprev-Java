package com.example.Odontoprev_Java.Model.Tipo_usuario;

public enum Enum_tipo_usuario {
    ADMINISTRADOR("Administrador"),
    DOUTOR("Doutor"),
    CLINICA("Clinica"),
    PACIENTE("Paciente");

    private String descricao;

    Enum_tipo_usuario(String descricao) {this.descricao = descricao;}

    @Override
    public String toString() {return descricao;}
}
