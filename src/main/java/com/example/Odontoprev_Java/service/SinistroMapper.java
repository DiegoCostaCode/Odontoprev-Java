package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoResponseDTO;
import com.example.Odontoprev_Java.DTO.sinistro.SinistroRequestDTO;
import com.example.Odontoprev_Java.DTO.sinistro.SinistroResponseDTO;
import com.example.Odontoprev_Java.Model.Atendimento;
import com.example.Odontoprev_Java.Model.Sinistro;
import org.springframework.stereotype.Service;

@Service
public class SinistroMapper {

    public Sinistro sinistroRequest(SinistroRequestDTO sinistroRequestDTO)
    {
        Sinistro sinistro = new Sinistro();

        sinistro.setDescricao(sinistroRequestDTO.descricao());
        sinistro.setCusto_excedente(sinistroRequestDTO.custo_excedente());
        sinistro.setAtendimento(sinistroRequestDTO.atendimento_id());

        return sinistro;
    }

    public SinistroResponseDTO sinistroToResponse(Sinistro sinistro)
    {
        return new SinistroResponseDTO(
                sinistro.getId(),
                sinistro.getData_sinistro(),
                sinistro.getDescricao(),
                sinistro.getCusto_excedente(),
                sinistro.getAtendimento()
        );
    }
}
