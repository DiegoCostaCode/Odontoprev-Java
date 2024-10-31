package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.paciente.PacienteRequestDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Repository.PacienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PacienteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void inserirOdontoPaciente(Date dataNascimento, Long enderecoId, String cpf, String email, String nome, String telefone) {
        entityManager.createNativeQuery("CALL INSERT_ODONTO_PACIENTE(:dataNascimento, :enderecoId, :cpf, :email, :nome, :telefone)")
                .setParameter("dataNascimento", dataNascimento)
                .setParameter("enderecoId", enderecoId)
                .setParameter("cpf", cpf)
                .setParameter("email", email)
                .setParameter("nome", nome)
                .setParameter("telefone", telefone)
                .executeUpdate();
    }

    @Transactional
    public void atualizarOdontoPaciente(Date dataNascimento, Long enderecoId, String cpf, String email, String nome, String telefone, Long idPaciente) {
        entityManager.createNativeQuery("CALL UPDATE_ODONTO_PACIENTE(:idPaciente,:dataNascimento, :enderecoId, :cpf, :email, :nome, :telefone)")
                .setParameter("dataNascimento", dataNascimento)
                .setParameter("enderecoId", enderecoId)
                .setParameter("cpf", cpf)
                .setParameter("email", email)
                .setParameter("nome", nome)
                .setParameter("telefone", telefone)
                .setParameter("idPaciente",idPaciente)
                .executeUpdate();
    }



}
