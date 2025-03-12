package com.example.Odontoprev_Java.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Odonto_Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Data_nascimento")
    private Date dataNascimento;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "E-mail", unique = true)
    private String email;

    @Column(name = "Telefone", unique = true)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "Plano_paciente")
    private Planos plano;
}