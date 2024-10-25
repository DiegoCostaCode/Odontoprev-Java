package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.clinica.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.clinica.ClinicaResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import org.springframework.stereotype.Service;

@Service
public class ClinicaMapper {

    public Clinica requestClinica(ClinicaRequestDTO clinicaRequestDTO)
    {
        Clinica clinica = new Clinica();

        clinica.setRazaoSocial(clinicaRequestDTO.razaoSocial());
        clinica.setDescricao(clinicaRequestDTO.descricao());
        clinica.setCnpj(clinicaRequestDTO.CNPJ());
        clinica.setEmailRepresentante(clinicaRequestDTO.emailRepresentante());

        return clinica;
    }

    public ClinicaResponseDTO clinicaResponseDTO(Clinica clinica)
    {
        return new ClinicaResponseDTO(
                clinica.getId(),
                clinica.getRazaoSocial(),
                clinica.getDescricao(),
                clinica.getCnpj(),
                clinica.getEmailRepresentante(),
                clinica.getEndereco()
        );
    }

}
