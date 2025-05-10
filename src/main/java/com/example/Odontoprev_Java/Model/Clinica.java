package com.example.Odontoprev_Java.model;


import com.example.Odontoprev_Java.model.agendamento.Agendamento;
import com.example.Odontoprev_Java.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Odonto_Clinicas")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razao_social")
    private String razaosocial;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "telefone")
    private String telefone;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "clinica", cascade = CascadeType.REMOVE)
    private List<Agendamento> agendamentos;
}
