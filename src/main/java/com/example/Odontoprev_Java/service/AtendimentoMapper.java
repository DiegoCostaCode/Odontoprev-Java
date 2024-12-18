package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Atendimento;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoMapper {

    public Atendimento atendimentoRequest(AtendimentoRequestDTO atendimentoRequestDTO)
    {
        Atendimento atendimento = new Atendimento();

        atendimento.setDataHoraAtendimento(atendimentoRequestDTO.diaHoraAtendimento());
        atendimento.setDescricao(atendimentoRequestDTO.descricao());
        atendimento.setCusto(atendimentoRequestDTO.custo());
        atendimento.setClinica(atendimentoRequestDTO.clinica());
        atendimento.setPaciente(atendimentoRequestDTO.paciente());

        return atendimento;
    }

    public AtendimentoResponseDTO atendimentoToResponse(Atendimento atendimento)
    {
        return new AtendimentoResponseDTO(
                atendimento.getId(),
                atendimento.getDataHoraAtendimento(),
                atendimento.getDescricao(),
                atendimento.getPaciente(),
                atendimento.getClinica(),
                atendimento.getCusto(),
                atendimento.getProcedimento()
        );
    }
}
