package com.example.Odontoprev_Java.service.procedures;

import com.example.Odontoprev_Java.DTO.paciente.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.service.PacienteMapper;
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

    @Autowired
    private PacienteMapper pacienteMapper;


    @Transactional
    public PacienteResponseDTO inserirOdontoPaciente(PacienteRequestDTO pacienteRequestDTO) {

        entityManager.createNativeQuery("CALL INSERT_ODONTO_PACIENTE(:dataNascimento, :enderecoId, :cpf, :email, :nome, :telefone)")
                .setParameter("dataNascimento", pacienteRequestDTO.dataNascimento())
                .setParameter("enderecoId", null) // ou o ID do endereço se aplicável
                .setParameter("cpf", pacienteRequestDTO.cpf())
                .setParameter("email", pacienteRequestDTO.email())
                .setParameter("nome", pacienteRequestDTO.nome())
                .setParameter("telefone", pacienteRequestDTO.telefone())
                .executeUpdate();

        Paciente pacienteInserido = (Paciente) entityManager.createQuery("SELECT p FROM Paciente p WHERE p.cpf = :cpf")
                .setParameter("cpf", pacienteRequestDTO.cpf())
                .getSingleResult();

        return pacienteMapper.pacienteResponseDTO(pacienteInserido);
    }

    @Transactional
    public PacienteResponseDTO updateOdontoPaciente(PacienteRequestDTO pacienteRequestDTO, Long idPaciente) {
        entityManager.createNativeQuery("CALL UPDATE_ODONTO_PACIENTE(:idPaciente,:dataNascimento, :enderecoId, :cpf, :email, :nome, :telefone)")
                .setParameter("dataNascimento", pacienteRequestDTO.dataNascimento())
                .setParameter("enderecoId", null)
                .setParameter("cpf", pacienteRequestDTO.cpf())
                .setParameter("email", pacienteRequestDTO.email())
                .setParameter("nome", pacienteRequestDTO.nome())
                .setParameter("telefone", pacienteRequestDTO.telefone())
                .setParameter("idPaciente",idPaciente)
                .executeUpdate();

        Paciente pacienteInserido = (Paciente) entityManager.createQuery("SELECT p FROM Paciente p WHERE p.cpf = :cpf")
                .setParameter("cpf", pacienteRequestDTO.cpf())
                .getSingleResult();

        return pacienteMapper.pacienteResponseDTO(pacienteInserido);
    }

    @Transactional
    public void deleteOdontoPaciente(Long idPaciente) {
        entityManager.createNativeQuery("CALL DELETE_ODONTO_PACIENTE(:idPaciente)")
                .setParameter("idPaciente", idPaciente)
                .executeUpdate();
    }



}
