package com.example.Odontoprev_Java.Model.Enums;

import java.util.Arrays;
import java.util.List;

public enum Enum_tipo_plano {
    DENTAL_JUNIOR("Dental Júnior",
            Arrays.asList(Enum_tipo_servico.ROLANS,
                    Enum_tipo_servico.LIMPEZA,
                    Enum_tipo_servico.EXTRACAODESISO,
                    Enum_tipo_servico.TRATAMENTOCANAL),
            29.90),

    BEM_ESTAR("Bem Estar",
            Arrays.asList(Enum_tipo_servico.ROLANS,
                    Enum_tipo_servico.EXTRACAODESISO,
                    Enum_tipo_servico.TRATAMENTOCANAL,
                    Enum_tipo_servico.PROTESESIMPLES),
            45.60),

    BEM_ESTAR_WHITE("Bem Estar White",
            Arrays.asList(Enum_tipo_servico.ROLANS,
                    Enum_tipo_servico.EXTRACAODESISO,
                    Enum_tipo_servico.TRATAMENTOCANAL,
                    Enum_tipo_servico.PLACADEBRUXISMO,
                    Enum_tipo_servico.CLAREAMENTO),
            89.90),

    BEM_ESTAR_PRO("Bem Estar Pro",
            Arrays.asList(Enum_tipo_servico.ROLANS,
                    Enum_tipo_servico.EXTRACAODESISO,
                    Enum_tipo_servico.DENTADURA,
                    Enum_tipo_servico.PROTESEPARCIALREMOVIVEL),
            99.90),

    BEM_ESTAR_ORTO("Bem Estar Orto",
            Arrays.asList(Enum_tipo_servico.ROLANS,
                    Enum_tipo_servico.APARELHOFIXOEMOVEL,
                    Enum_tipo_servico.MANUTENCAODEAPARELHOS),
            139.90),

    BEM_ESTAR_ORTO_WHITE("Bem Estar Orto White",
            Arrays.asList(Enum_tipo_servico.ROLANS,
                    Enum_tipo_servico.APARELHOFIXOEMOVEL,
                    Enum_tipo_servico.MANUTENCAODEAPARELHOS,
                    Enum_tipo_servico.CLAREAMENTO),
            159.90);

    private String nome;
    private List<Enum_tipo_servico> servicos;
    private double preco;

    Enum_tipo_plano(String nome, List<Enum_tipo_servico> servicos, double preco) {
        this.nome = nome;
        this.servicos = servicos;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public List<Enum_tipo_servico> getServicos() {
        return servicos;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " - Preço: " + preco + " | Serviços: " + servicos;
    }
}
