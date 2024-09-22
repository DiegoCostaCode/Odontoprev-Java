package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "CH_CIDADE")
public class Cidade{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "CIDADE")
    private String cidade;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "Estado_cidade_id")
    private Estado estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
