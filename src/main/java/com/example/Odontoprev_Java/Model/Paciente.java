package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "Cpf")
    private String cpf;

    @Column(name = "Email")
    private String email;

    @Column(name = "Telefone")
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Carteirinha")
    private Carteirinha carteirinha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Endereco_id")
    private Endereco endereco;

    @OneToMany
    @JoinColumn(name = "historico_atendimentos")
    private List<Atendimento> atendimentos = new ArrayList<>();

    public Paciente() {}

    public Paciente(String nome, LocalDate dataNascimento, String cpf, String email, String telefone, Carteirinha carteirinha, Endereco endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.carteirinha = carteirinha;
        this.endereco = endereco;
    }

}