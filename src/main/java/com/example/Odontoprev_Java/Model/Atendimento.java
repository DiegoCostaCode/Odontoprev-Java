package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Atendimento")
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_atendimento;

    @Column(name = "Data")
    private LocalDate data;

    @Column(name = "Descrição")
    private String descricao;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "Paciente_id")
    private Paciente paciente;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "Clinica_id")
    private Clinica clinica;

    @Column(name = "Custo")
    private double custo;


}
