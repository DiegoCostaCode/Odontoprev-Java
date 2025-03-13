package com.example.Odontoprev_Java.Model.procedimento;

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
    @Column(name = "procedimento", unique = true)
    private Enum_procedimento procedimento;

    @Column(name = "preco")
    private double preco;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
