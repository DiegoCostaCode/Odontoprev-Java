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

    @Column(name = "Numero")
    private long numero = gerarNumero();

    @Column (name = "Emissao")
    private Date emissao = new Date();

    @Column (name = "Validade")
    private Date validade = calcularValidade(1);

    @OneToOne
    @JoinColumn(name = "Paciente_id")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "Plano_id")
    private Plano plano;

    private Date calcularValidade(int anos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.emissao);
        cal.add(Calendar.YEAR, anos);
        return cal.getTime();
    }


    public long gerarNumero() {
        return (long) (Math.random() * 1_000_000_000_000_000_00L);
    }


}
