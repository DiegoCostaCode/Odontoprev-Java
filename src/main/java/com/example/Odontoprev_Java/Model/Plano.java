package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_plano;
import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CH_PLANO")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_PLANO")
    private Enum_tipo_plano tipo_plano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum_tipo_plano getTipo_plano() {
        return tipo_plano;
    }

    public void setTipo_plano(Enum_tipo_plano tipo_plano) {
        this.tipo_plano = tipo_plano;
    }
}
