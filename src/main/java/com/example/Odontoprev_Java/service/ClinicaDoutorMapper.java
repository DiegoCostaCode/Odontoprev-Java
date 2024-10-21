package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.clinica.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorResponseDTO;
import com.example.Odontoprev_Java.Model.ClinicaDoutor;
import org.springframework.stereotype.Service;

@Service
public class ClinicaDoutorMapper {

    public ClinicaDoutor clinicaDoutorRequest(ClinicaDoutorRequestDTO clinicaDoutorRequestDTO)
    {
        ClinicaDoutor clinicaDoutor = new ClinicaDoutor();

        clinicaDoutor.setClinica(clinicaDoutorRequestDTO.clinicaId());
        clinicaDoutor.setDoutor(clinicaDoutorRequestDTO.doutorId());
        clinicaDoutor.setDataRelacionamento(clinicaDoutorRequestDTO.dataRelacionamento());
        clinicaDoutor.setDataFimRelacionamento(clinicaDoutorRequestDTO.dataFimRelacionamento());

        return clinicaDoutor;
    }

    public ClinicaDoutorResponseDTO clinicaDoutorToResponse(ClinicaDoutor clinicaDoutor)
    {
        return new ClinicaDoutorResponseDTO(
                clinicaDoutor.getId(),
                clinicaDoutor.getClinica(),
                clinicaDoutor.getDoutor(),
                clinicaDoutor.getDataRelacionamento(),
                clinicaDoutor.getDataFimRelacionamento()
        );
    }
}
