package com.example.Odontoprev_Java.Model;


import com.example.Odontoprev_Java.Model.usuario.Usuario;
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
}
