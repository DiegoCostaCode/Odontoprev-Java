package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
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

    @OneToMany(mappedBy = "Odonto_Paciente" , fetch = FetchType.LAZY)
    @JoinColumn(name = "historico_atendimentos")
    private List<Atendimento> atendimentos = new ArrayList<>();

}