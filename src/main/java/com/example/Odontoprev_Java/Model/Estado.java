package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Enums.Enum_estado;
import jakarta.persistence.*;

@Entity
@Table(name = "CH_ESTADO")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ESTADO")
    private Enum_estado estado;
    @OneToOne
    @JoinColumn(name = "Estado_pais_id")
    private Pais pais;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enum_estado getEstado() {
        return estado;
    }

    public void setEstado(Enum_estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
