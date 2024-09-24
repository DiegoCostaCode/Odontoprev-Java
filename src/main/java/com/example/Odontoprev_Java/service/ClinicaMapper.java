package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Plano;
import org.springframework.stereotype.Service;

@Service
public class ClinicaMapper {

    public Clinica requestRecordToClinica(ClinicaRequestDTO clinicaRequestDto)
    {
        Clinica clinica = new Clinica();

        clinica.setRazaoSocial(clinicaRequestDto.razaoSocial());
        clinica.setServicos(clinicaRequestDto.servicos());
        clinica.setEmailRepresentante(clinicaRequestDto.emailRepresentante());
        clinica.setEndereco(clinicaRequestDto.endereco());
        return clinica;
    }

    public ClinicaResponseDTO clinicaToResponseDto(Clinica clinica)
    {
        return new ClinicaResponseDTO(
                clinica.getId(),
                clinica.getRazaoSocial(),
                clinica.getServicos(),
                clinica.getEmailRepresentante(),
                clinica.getEndereco(),
                clinica.getDoutores()
        );
    }
}
