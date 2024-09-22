package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Enums.Enum_paises;
import jakarta.persistence.*;

@Entity
@Table(name = "CH_PAIS")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Pais")
    private Enum_paises pais;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enum_paises getPais() {
        return pais;
    }

    public void setPais(Enum_paises pais) {
        this.pais = pais;
    }
}
