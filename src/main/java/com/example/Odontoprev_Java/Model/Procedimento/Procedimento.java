package com.example.Odontoprev_Java.Model.Procedimento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Procedimento")
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_procedimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "Descricao")
    private Enum_procedimento descricao;
}
