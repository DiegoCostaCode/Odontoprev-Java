package com.example.Odontoprev_Java.Model.procedimento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Procedimento")
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Procedimento")
    private Enum_procedimento procedimento;

    @Column(name = "Preco")
    private double preco;

    @Column(name = "Data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Procedimento() {}

    public Procedimento(long id, Enum_procedimento procedimento, double preco, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.procedimento = procedimento;
        this.preco = preco;
        this.dataAtualizacao = dataAtualizacao;
    }
}
