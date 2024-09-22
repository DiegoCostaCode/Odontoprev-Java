package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CH_SERVICOS")
public class Servicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipos_servicos_id")
    private Tipo_servico Tipos_servicos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo_servico getTipos_servicos() {
        return Tipos_servicos;
    }

    public void setTipos_servicos(Tipo_servico tipos_servicos) {
        Tipos_servicos = tipos_servicos;
    }
}
