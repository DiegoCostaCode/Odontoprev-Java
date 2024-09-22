package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "CH_BAIRRO")
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BAIRRO")
    private String Bairro;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "Bairro_cidade")
    private Cidade Cidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public com.example.Odontoprev_Java.Model.Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(com.example.Odontoprev_Java.Model.Cidade cidade) {
        Cidade = cidade;
    }
}
