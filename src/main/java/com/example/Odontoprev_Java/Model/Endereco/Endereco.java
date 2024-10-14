package com.example.Odontoprev_Java.Model.Endereco;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CH_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_endereco;

    @Column(name = "Rua")
    private String rua;

    @Column(name = "Complemento")
    private String complemento;

    @Column(name = "CEP")
    private String CEP;

    @OneToMany
    @JoinColumn(name ="Id_cidade")
    private Cidade cidade_id;


}
