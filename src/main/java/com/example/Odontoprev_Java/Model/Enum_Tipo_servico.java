package com.example.Odontoprev_Java.Model;

public enum Enum_Tipo_servico {
    ROLANS("Rol ANS."),
    LIMPEZA("Limpeza."),
    EXTRACAODESISO("Extração de Siso."),
    TRATAMENTOCANAL("Tratamento e retratamento de canal."),
    PROTESESIMPLES("Prótese Simples"),
    PROTESEPARCIALREMOVIVEL("Prótese parcial removível"),
    DENTADURA("Prótese removível total"),
    APARELHOFIXOEMOVEL("Aparelho fixo e móvel."),
    MANUTENCAODEAPARELHOS("Manutenção de aparelhos."),
    PLACADEBRUXISMO("Placa de bruxismo"),
    CLAREAMENTO("Clareamento estético com Gel");

    private String descricao;

    Enum_Tipo_servico(String descricao) {this.descricao = descricao;}

    @Override
    public String toString() {return descricao;}
}
