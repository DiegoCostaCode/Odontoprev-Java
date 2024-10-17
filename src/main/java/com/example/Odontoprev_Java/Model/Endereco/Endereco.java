package com.example.Odontoprev_Java.Model.Endereco;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_endereco;

    @Column(name = "Rua")
    private String rua;

    @Column(name = "Complemento")
    private String complemento;

    @Column(name = "Numero")
    private int numero;

    @Column(name = "CEP")
    private String CEP;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade")
    private Cidade cidade_id;

}
