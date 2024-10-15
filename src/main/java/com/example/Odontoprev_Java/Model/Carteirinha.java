package com.example.Odontoprev_Java.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "Odonto_Carteirinha")
public class Carteirinha {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_carteirinha;

    @Column (name = "Numero")
    private int numero;

    @Column (name = "Emissao")
    private Date emissao;

    @Column (name = "Validade")
    private Date validade;
    @OneToOne
    @JoinColumn(name = "Paciente_id")
    private Paciente id_paciente;
    @OneToOne
    @JoinColumn(name = "Plano_id")
    private Plano id_plano;



    public Carteirinha() {
        this.emissao = new Date();
        this.validade = calcularValidade(1);
    }


    private Date calcularValidade(int anos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.emissao);
        cal.add(Calendar.YEAR, anos);
        return cal.getTime();
    }


}
