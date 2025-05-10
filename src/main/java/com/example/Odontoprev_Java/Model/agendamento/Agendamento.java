package com.example.Odontoprev_Java.model.agendamento;

import com.example.Odontoprev_Java.model.Clinica;
import com.example.Odontoprev_Java.model.Paciente;
import com.example.Odontoprev_Java.model.procedimento.Procedimento;
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

    @Column(name = "Finalizado_em")
    private LocalDateTime finalizadoEm;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private Enum_status_agendamento status = Enum_status_agendamento.MARCADA;

    @ManyToOne()
    @JoinColumn(name = "Paciente_id")
    private Paciente paciente;

    @ManyToOne()
    @JoinColumn(name = "Clinica_id")
    private Clinica clinica;

    @Column(name = "Preco_atendimento")
    private Double precoAtendimento;

    @Column(name = "Descricao_atendimento")
    private String descricaoAtendimento = "Atendimento marcado!";

    @ManyToOne()
    @JoinColumn(name = "Procedimento_id")
    private Procedimento procedimento;

}
