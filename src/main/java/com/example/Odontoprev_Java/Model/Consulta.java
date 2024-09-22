package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;
import org.hibernate.mapping.Join;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "CH_CONSULTA")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "AGENDAMENTO_CONSULTA")
    private LocalDate agendamento;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "SERVICO_ID")
    private Servicos servico;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "DOUTOR_ID")
    private Doutor Doutor;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLINICA_ID")
    private Clinica clinica;

    @Column(name = "OBSERVACOES", length = 500)
    private String observacoes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(LocalDate agendamento) {
        this.agendamento = agendamento;
    }

    public Servicos getServico() {
        return servico;
    }

    public void setServico(Servicos servico) {
        this.servico = servico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public com.example.Odontoprev_Java.Model.Doutor getDoutor() {
        return Doutor;
    }

    public void setDoutor(com.example.Odontoprev_Java.Model.Doutor doutor) {
        Doutor = doutor;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }
}
