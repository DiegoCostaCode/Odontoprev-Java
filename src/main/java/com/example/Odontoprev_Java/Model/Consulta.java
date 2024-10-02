package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.persistence.*;
import org.hibernate.mapping.Join;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CH_CONSULTA")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DATA")
    private LocalDate agendamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "SERVICOS_ID")
    private Enum_tipo_servico servico;

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

    public Enum_tipo_servico getServico() {
        return servico;
    }

    public void setServico(Enum_tipo_servico servico) {
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
