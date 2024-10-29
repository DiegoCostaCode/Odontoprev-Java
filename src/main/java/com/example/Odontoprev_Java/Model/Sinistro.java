package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Sinistro")
public class Sinistro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Data_sinistro")
    private LocalDate data_sinistro = LocalDate.now();

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Custo_excedente")
    private double custo_excedente;

    @ManyToOne
    @JoinColumn(name = "Atendimento_id")
    private Atendimento atendimento;
}
