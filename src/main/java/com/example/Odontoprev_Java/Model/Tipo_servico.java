package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

@Entity
public class Tipo_servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_SERVICO")
    private Enum_Tipo_servico tipoServico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum_Tipo_servico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Enum_Tipo_servico tipoServico) {
        this.tipoServico = tipoServico;
    }
}
