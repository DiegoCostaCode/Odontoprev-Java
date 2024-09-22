package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CH_CLINICA")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "SERVICOS_ID")
    private List<Servicos> servicos = new ArrayList<>();
    @Column(name = "EMAIL_REPRESENTANTE")
    private String emailRepresentante;
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "doutor_clinica",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "doutor_id")
    )
    private Set<Doutor> doutores;

    public long getId() {
        return id;
    }

    public Set<Doutor> getDoutores() {
        return doutores;
    }

    public void setDoutores(Set<Doutor> doutores) {
        this.doutores = doutores;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public List<Servicos> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servicos> servicos) {
        this.servicos = servicos;
    }

    public String getEmailRepresentante() {
        return emailRepresentante;
    }

    public void setEmailRepresentante(String emailRepresentante) {
        this.emailRepresentante = emailRepresentante;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
