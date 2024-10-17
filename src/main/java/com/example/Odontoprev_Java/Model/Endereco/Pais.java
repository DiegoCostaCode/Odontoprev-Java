package com.example.Odontoprev_Java.Model.Endereco;

import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_paises;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pais;

    private Enum_paises nome;
}
