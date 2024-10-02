package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.ConsultaRequestDTO;
import com.example.Odontoprev_Java.DTO.ConsultaResponseDTO;
import com.example.Odontoprev_Java.Model.Consulta;
import org.springframework.stereotype.Service;

@Service
public class ConsultaMapper {



    public Consulta requestToAgendamento(ConsultaRequestDTO consultaRequestDTO) {
        Consulta consulta = new Consulta();
        consulta.setAgendamento(consultaRequestDTO.agendamento());
        consulta.setServico(consultaRequestDTO.servico());
        consulta.setClinica(consultaRequestDTO.clinicaId());
        consulta.setObservacoes(consultaRequestDTO.observacoes());
        return consulta;
    }

    public ConsultaResponseDTO consultaToResponseDto(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getAgendamento(),
                consulta.getServico(),
                consulta.getUsuario(),
                consulta.getDoutor(),
                consulta.getClinica(),
                consulta.getObservacoes()
        );
    }
}
