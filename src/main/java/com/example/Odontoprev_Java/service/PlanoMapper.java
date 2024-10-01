package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.Plano;
import org.springframework.stereotype.Service;

@Service
public class PlanoMapper {

    //Record para Plano
    public Plano requestRecordToPlano(PlanoRequestDTO planoRequestDto)
    {
        Plano plano = new Plano();

        plano.setServicos(planoRequestDto.servicos());
        return plano;
    }

    public PlanoResponseDTO planoToResponseDto(Plano plano)
    {
        return new PlanoResponseDTO(
                plano.getId(),
                plano.getServicos()
        );
    }
}
