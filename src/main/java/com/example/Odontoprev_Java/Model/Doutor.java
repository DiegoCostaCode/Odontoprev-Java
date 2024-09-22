package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CH_DOUTOR")
public class Doutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "CRO")
    private String cro;
    @Column(name = "E-MAIL")
    private String email;
    @Column(name = "NASCIMENTO")
    private Date nascimento;
    @Column(name = "TELEFONE")
    private String telefone;
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "doutor_clinica_id")
    private List<Clinica> clinicas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Clinica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<Clinica> clinicas) {
        this.clinicas = clinicas;
    }
}
