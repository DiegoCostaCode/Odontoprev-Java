package com.example.Odontoprev_Java.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CH_DOUTOR_CLINICA")
public class DoutorClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "doutor_id")
    private Doutor doutor;

    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    private Date dataRegistro;

    // Construtor
    public DoutorClinica() {
        this.dataRegistro = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Doutor getDoutor() {
        return doutor;
    }

    public void setDoutor(Doutor doutor) {
        this.doutor = doutor;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}

