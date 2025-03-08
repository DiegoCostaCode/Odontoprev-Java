package com.example.Odontoprev_Java.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Odonto_Clinicas")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "razao_social")
    private String razaosocial;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "E-mail")
    private String email;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Senha")
    private String senha;

}
