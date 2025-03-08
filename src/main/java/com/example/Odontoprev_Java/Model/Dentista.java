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
@Table(name = "Odonto_Dentistas")
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "CRM")
    private String CRM;

    @Column(name = "CPF")
    private String CPF;

    @Column(name = "E-mail")
    private String email;

    @Column(name = "Telefone")
    private String telefone;
}
