package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Enums.Enum_estado;
import com.example.Odontoprev_Java.Model.Enums.Enum_paises;
import jakarta.persistence.*;

@Entity
@Table(name = "CH_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "NUMERO")
    private int numero;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "ESTADO")
    private Enum_estado estado;

    @Column(name = "PAIS")
    private Enum_paises pais;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Enum_estado getEstado() {
        return estado;
    }

    public void setEstado(Enum_estado estado) {
        this.estado = estado;
    }

    public Enum_paises getPais() {
        return pais;
    }

    public void setPais(Enum_paises pais) {
        this.pais = pais;
    }
}
