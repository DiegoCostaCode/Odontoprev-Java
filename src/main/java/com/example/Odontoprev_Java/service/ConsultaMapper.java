package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Atendimento;
import org.springframework.stereotype.Service;

@Service
public class ConsultaMapper {



    public Atendimento requestToAgendamento(AtendimentoRequestDTO atendimentoRequestDTO) {
        Atendimento atendimento = new Atendimento();
        atendimento.setAgendamento(atendimentoRequestDTO.agendamento());
        atendimento.setServico(atendimentoRequestDTO.servico());
        atendimento.setClinica(atendimentoRequestDTO.clinicaId());
        atendimento.setObservacoes(atendimentoRequestDTO.observacoes());
        return atendimento;
    }

    public AtendimentoResponseDTO consultaToResponseDto(Atendimento atendimento) {
        return new AtendimentoResponseDTO(
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
