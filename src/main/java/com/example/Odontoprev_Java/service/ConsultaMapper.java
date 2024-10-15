package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.consulta.ConsultaRequestDTO;
import com.example.Odontoprev_Java.DTO.consulta.ConsultaResponseDTO;
import com.example.Odontoprev_Java.Model.Atendimento;
import org.springframework.stereotype.Service;

@Service
public class ConsultaMapper {



    public Atendimento requestToAgendamento(ConsultaRequestDTO consultaRequestDTO) {
        Atendimento atendimento = new Atendimento();
        atendimento.setAgendamento(consultaRequestDTO.agendamento());
        atendimento.setServico(consultaRequestDTO.servico());
        atendimento.setClinica(consultaRequestDTO.clinicaId());
        atendimento.setObservacoes(consultaRequestDTO.observacoes());
        return atendimento;
    }

    public ConsultaResponseDTO consultaToResponseDto(Atendimento atendimento) {
        return new ConsultaResponseDTO(
                atendimento.getId(),
                atendimento.getAgendamento(),
                atendimento.getServico(),
                atendimento.getUsuario(),
                atendimento.getDoutor(),
                atendimento.getClinica(),
                atendimento.getObservacoes()
        );
    }
}
