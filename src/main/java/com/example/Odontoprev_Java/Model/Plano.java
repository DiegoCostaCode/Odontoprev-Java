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

    @ElementCollection(targetClass = Enum_tipo_plano.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "CH_PLANO_SERVIÇOS", joinColumns = @JoinColumn(name = "plano_id"))
    @Column(name = "servicos")
    private List<Enum_tipo_plano> servicos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Enum_tipo_plano> getServicos() {
        return servicos;
    }

    public void setServicos(List<Enum_tipo_plano> servicos) {
        this.servicos = servicos;
    }
}
