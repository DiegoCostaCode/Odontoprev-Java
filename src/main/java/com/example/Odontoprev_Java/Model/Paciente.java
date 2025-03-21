package com.example.Odontoprev_Java.Model;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Column(name = "Nome", unique = true)
    private String nome;

    @Column(name = "Data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "Telefone", unique = true)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "plano_id", referencedColumnName = "id")
    private Planos plano;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}