package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimento.ProdecimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import org.springframework.stereotype.Service;

@Service
public class ProdecimentoMapper {

    public Procedimento procedimentoRequest(ProcedimentoRequestDTO procedimentoRequestDTO)
    {
        Procedimento procedimento = new Procedimento();

        procedimento.setDescricao(procedimentoRequestDTO.descricao());

        return procedimento;
    }

    public ProdecimentoResponseDTO procedimentoToResponse(Procedimento procedimento)
    {
        return new ProdecimentoResponseDTO(
                procedimento.getId(),
                procedimento.getDescricao()
        );
    }
}
