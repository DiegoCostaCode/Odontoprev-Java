package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CH_CARTEIRINHA")
public class Carteirinha {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name= "NUMERO_CARTEIRINHA", updatable = false, nullable = false)
    private UUID id;
    @Column (name = "EMISSAO")
    private Date emissao;
    @Column (name = "VALIDADE")
    private Date validade;
    @OneToOne(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    @JoinColumn(name = "Id_usuario")
    private Usuario usuario;
    @OneToOne(cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinColumn(name = "Id_plano")
    private Plano plano;

    // Construtor
    public Carteirinha() {
        this.emissao = new Date();
        this.validade = calcularValidade(1);
    }

    // Método para calcular validade, adicionando anos à data de emissão
    private Date calcularValidade(int anos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.emissao);
        cal.add(Calendar.YEAR, anos);
        return cal.getTime();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
}
