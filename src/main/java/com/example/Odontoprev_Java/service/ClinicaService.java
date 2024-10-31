package com.example.Odontoprev_Java.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClinicaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void inserirOdontoClinica(Long enderecoId, String cnpj, String descricao, String emailRepresentante, String razaoSocial) {
        entityManager.createNativeQuery("CALL INSERT_ODONTO_CLINICA(:enderecoId, :cnpj, :descricao, :emailRepresentante, :razaoSocial)")
                .setParameter("enderecoId", enderecoId)
                .setParameter("cnpj", cnpj)
                .setParameter("descricao", descricao)
                .setParameter("emailRepresentante", emailRepresentante)
                .setParameter("razaoSocial", razaoSocial)
                .executeUpdate();
    }

}

