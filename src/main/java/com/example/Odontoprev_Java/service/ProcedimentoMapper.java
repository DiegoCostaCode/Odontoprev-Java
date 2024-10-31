package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import org.springframework.stereotype.Service;

@Service
public class ProcedimentoMapper {

    public Procedimento procedimentoRequest(ProcedimentoRequestDTO procedimentoRequestDTO)
    {
        Procedimento procedimento = new Procedimento();

        procedimento.setDescricao(procedimentoRequestDTO.descricao());

        return procedimento;
    }

    public ProcedimentoResponseDTO procedimentoToResponse(Procedimento procedimento)
    {
        return new ProcedimentoResponseDTO(
                procedimento.getId(),
                procedimento.getDescricao()
        );
    }
}
