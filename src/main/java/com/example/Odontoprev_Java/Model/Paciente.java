package com.example.Odontoprev_Java.Model;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Data_nascimento")
    private Date dataNascimento;

    @Column(name = "Cpf", unique = true)
    private String cpf;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Telefone", unique = true)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Endereco_id")
    private Endereco endereco;


    public Paciente() {}

    public Paciente(String nome, Date dataNascimento, String cpf, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

}