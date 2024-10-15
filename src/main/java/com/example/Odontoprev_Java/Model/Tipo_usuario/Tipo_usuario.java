package com.example.Odontoprev_Java.Model.Tipo_usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Tipo_usuario")
public class Tipo_usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Atribuicao")
    private com.example.Odontoprev_Java.Model.Tipo_usuario.Enum_tipo_usuario atribuicao;
}
