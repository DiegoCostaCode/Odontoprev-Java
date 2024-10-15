package com.example.Odontoprev_Java.Model.Endereco;

import com.example.Odontoprev_Java.Model.Endereco.Enum.Enum_estado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Odonto_Estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_estado;

    @Column(name = "Estado")
    private Enum_estado estado;

    @OneToOne
    @JoinColumn(name ="Id_pais")
    private Pais pais_id;
}
