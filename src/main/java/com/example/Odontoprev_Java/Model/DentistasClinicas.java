package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Odonto_Dentistas_Clinicas")
public class DentistasClinicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Clinica_id")
    private Clinica clinica;

    @ManyToOne
    @JoinColumn(name = "Doutor_id")
    private Dentista dentista;

    @Column(name = "Data_relacionamento")
    private LocalDateTime dataRelacionamento = LocalDateTime.now();

    @Column(name = "Data_fim_relacionamento")
    private LocalDateTime dataFimRelacionamento = LocalDateTime.now();

}



