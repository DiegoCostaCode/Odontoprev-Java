package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Odonto_Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "CH_DATA_NASCIMENTO")
    private LocalDate dataNascimento;
    @Column(name = "CH_CPF")
    private String cpf;
    @Column(name = "CH_EMAIL")
    private String email;
    @Column(name = "CH_TELEFONE")
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CARTEIRINHA_ID")
    private Carteirinha carteirinha;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "historico_consultas")
    private List<Atendimento> atendimentos = new ArrayList<>();

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Atendimento> getConsultas() {
        return atendimentos;
    }

    public void setConsultas(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Carteirinha getCarteirinha() {
        return carteirinha;
    }

    public void setCarteirinha(Carteirinha carteirinha) {
        this.carteirinha = carteirinha;
    }
}