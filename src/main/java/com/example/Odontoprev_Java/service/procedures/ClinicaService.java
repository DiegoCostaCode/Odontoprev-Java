package com.example.Odontoprev_Java.service.procedures;

import com.example.Odontoprev_Java.DTO.clinica.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.clinica.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.service.ClinicaMapper;
import com.example.Odontoprev_Java.service.PacienteMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClinicaMapper clinicaMapper;


    @Transactional
    public ClinicaResponseDTO inserirOdontoClinica(ClinicaRequestDTO clinicaRequestDTO) {

        entityManager.createNativeQuery("CALL INSERT_ODONTO_CLINICA(:cnpj, :descricao, :email_representante, :razao_social, :enderecoId)")
                .setParameter("cnpj", clinicaRequestDTO.CNPJ())
                .setParameter("descricao", clinicaRequestDTO.descricao())
                .setParameter("email_representante", clinicaRequestDTO.emailRepresentante())
                .setParameter("razao_social", clinicaRequestDTO.razaoSocial())
                .setParameter("enderecoId", null)
                .executeUpdate();

        Clinica clinicaInserido = (Clinica) entityManager.createQuery("SELECT p FROM Clinica p WHERE p.cnpj = :cnpj")
                .setParameter("cnpj", clinicaRequestDTO.CNPJ())
                .getSingleResult();

        return clinicaMapper.clinicaToResponse(clinicaInserido);
    }

    @Transactional
    public ClinicaResponseDTO updateOdontoClinica(ClinicaRequestDTO clinicaRequestDTO, Long idClinica) {
        entityManager.createNativeQuery("CALL UPDATE_ODONTO_CLINICA(:idClinica, :cnpj, :descricao, :email_representante, :razao_social, :enderecoId)")
                .setParameter("idClinica", idClinica)
                .setParameter("cnpj", clinicaRequestDTO.CNPJ())
                .setParameter("descricao", clinicaRequestDTO.descricao())
                .setParameter("email_representante", clinicaRequestDTO.emailRepresentante())
                .setParameter("razao_social", clinicaRequestDTO.razaoSocial())
                .setParameter("enderecoId", null)
                .executeUpdate();

        Clinica clinicaAtualizada = (Clinica) entityManager.createQuery("SELECT c FROM Clinica c WHERE c.id = :id")
                .setParameter("id", idClinica)
                .getSingleResult();

        return clinicaMapper.clinicaToResponse(clinicaAtualizada);
    }

    @Transactional
    public void deleteOdontoClinica(Long idClinica) {

        entityManager.createNativeQuery("CALL DELETE_ODONTO_CLINICA(:idClinica)")
                .setParameter("idClinica", idClinica)
                .executeUpdate();
    }

}

