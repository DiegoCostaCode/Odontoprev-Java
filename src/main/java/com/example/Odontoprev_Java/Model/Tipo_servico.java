package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.persistence.*;

@Entity
public class Tipo_servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_SERVICO")
    private Enum_tipo_servico tipoServico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum_tipo_servico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Enum_tipo_servico tipoServico) {
        this.tipoServico = tipoServico;
    }
}
