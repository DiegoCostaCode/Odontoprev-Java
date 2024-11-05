package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Plano")
public class Plano{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Preco")
    private double preco;

    @Column(name = "Ativo")
    private boolean ativo = false;

}
