package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Doutor")
public class Doutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "CRM")
    private String CRM;

    @OneToMany(mappedBy ="Odonto_Doutor", fetch = FetchType.LAZY)
    private List<ClinicaDoutor> clinicaDoutores;

    @OneToOne
    @JoinColumn(name = "Endereco_id")
    private Endereco endereco;
}
