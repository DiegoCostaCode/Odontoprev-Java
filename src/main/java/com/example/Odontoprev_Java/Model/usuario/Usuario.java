package com.example.Odontoprev_Java.Model.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Odonto_Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="senha", unique = true)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_usuario")
    private Enum_tipo_usuario tipo;

    @Column(name="data_cadastramento")
    private LocalDateTime dataCadastramento;

}
