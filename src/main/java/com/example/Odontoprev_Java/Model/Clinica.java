package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Clinica")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Razão_social")
    private String razaoSocial;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "Email_representante")
    private String emailRepresentante;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<ClinicaDoutor> clinicaDoutores;

    @OneToMany()
    @JoinColumn(name = "historico_atendimentos")
    private List<Atendimento> atendimentos;

}
