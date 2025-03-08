package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.procedimento.Procedimento;
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
@Table(name = "Odonto_Agendamento")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Data_agendamento")
    private LocalDateTime dataAgendamento;

    @Column(name = "Data_atendimento")
    private LocalDateTime dataAtendimento;

    @ManyToOne()
    @JoinColumn(name = "Paciente_id")
    private Paciente paciente;

    @ManyToOne()
    @JoinColumn(name = "Clinica_id")
    private Clinica clinica;

    @Column(name = "Preco_atendimento")
    private double precoAtendimento;

    @ManyToOne()
    @JoinColumn(name = "Procedimento_id")
    private Procedimento procedimento;

}
