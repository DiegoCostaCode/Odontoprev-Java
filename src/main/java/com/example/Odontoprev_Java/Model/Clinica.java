package com.example.Odontoprev_Java.Model;

import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_plano;
import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CH_CLINICA")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;
    @ElementCollection(targetClass = Enum_tipo_servico.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "clinica_servicos", joinColumns = @JoinColumn(name = "clinica_id"))
    @Column(name = "servicos")
    private List<Enum_tipo_servico> servicos = new ArrayList<>();
    @Column(name = "EMAIL_REPRESENTANTE")
    private String emailRepresentante;
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "clinica_doutor", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "clinica_id"), // Coluna da tabela 'Clinica'
            inverseJoinColumns = @JoinColumn(name = "doutor_id") // Coluna da tabela 'Doutor'
    )
    private List<Doutor> doutores;

    public long getId() {
        return id;
    }

    public List<Doutor> getDoutores() {
        return doutores;
    }

    public void setDoutores(List<Doutor> doutores) {
        this.doutores = doutores;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public List<Enum_tipo_servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Enum_tipo_servico> servicos) {
        this.servicos = servicos;
    }

    public String getEmailRepresentante() {
        return emailRepresentante;
    }

    public void setEmailRepresentante(String emailRepresentante) {
        this.emailRepresentante = emailRepresentante;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
