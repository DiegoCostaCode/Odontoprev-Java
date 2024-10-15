package com.example.Odontoprev_Java.Model.Endereco;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cidade;

    @Column(name = "Cidade")
    private String cidade;

    @OneToOne
    @JoinColumn(name ="Id_estado")
    private Estado estado_id;

}
