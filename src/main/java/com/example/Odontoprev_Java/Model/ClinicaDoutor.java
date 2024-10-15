package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Clinica_Doutor")
public class ClinicaDoutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    @ManyToOne
    @JoinColumn(name = "doutor_id")
    private Doutor doutor;

    @Column(name = "data_relacionamento")
    private LocalDate dataRelacionamento;

    @Column(name = "data_fim_relacionamento")
    private LocalDate dataFimRelacionamento;

}



