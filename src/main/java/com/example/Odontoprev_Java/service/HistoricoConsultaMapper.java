package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.HistoricoConsultaRequestDTO;
import com.example.Odontoprev_Java.DTO.HistoricoConsultaResponseDTO;
import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.HistoricoConsulta;
import com.example.Odontoprev_Java.Model.Plano;
import org.springframework.stereotype.Service;

@Service
public class HistoricoConsultaMapper {

    public HistoricoConsulta requestRecordToHistoricoConsulta
            (HistoricoConsultaRequestDTO historicoConsultaRequestDTO)
    {
        HistoricoConsulta historicoConsulta = new HistoricoConsulta();

        historicoConsulta.setUsuario(historicoConsultaRequestDTO.usuario());
        historicoConsulta.setDoutor(historicoConsultaRequestDTO.doutor());
        historicoConsulta.setClinica(historicoConsultaRequestDTO.clinica());
        historicoConsulta.setConsultas(historicoConsultaRequestDTO.consultas());

        return historicoConsulta;
    }

    public HistoricoConsultaResponseDTO historicoConsultaToResponseDTO
            (HistoricoConsulta historicoConsulta)
    {
        return new HistoricoConsultaResponseDTO(
                historicoConsulta.getId(),
                historicoConsulta.getUsuario(),
                historicoConsulta.getClinica(),
                historicoConsulta.getDoutor(),
                historicoConsulta.getConsultas()
        );
    }
}
