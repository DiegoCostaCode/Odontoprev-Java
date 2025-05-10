package com.example.Odontoprev_Java.model.procedimento;

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
@Table(name = "Odonto_Procedimento")
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Titulo", unique = true)
    private Enum_procedimento titulo;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Valor_cobertura")
    private double valorCobertura;

    @Column(name = "Status", length = 1)
    private String status = "F";

    @Column(name = "Data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
