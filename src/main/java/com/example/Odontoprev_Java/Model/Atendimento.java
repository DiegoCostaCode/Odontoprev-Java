package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Atendimento")
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_atendimento;

    @Column(name = "Data")
    private Date data = new Date();

    @Column(name = "Descrição")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Paciente_id")
    private Paciente paciente;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "Clinica_id")
    private Clinica clinica;

    @Column(name = "Custo")
    private double custo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Procedimento_id")
    private Procedimento procedimento;


}
