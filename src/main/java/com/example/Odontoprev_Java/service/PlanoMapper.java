package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.DTO.UsuarioRequestDto;
import com.example.Odontoprev_Java.DTO.UsuarioResponseDto;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class PlanoMapper {

    //Record para Plano
    public Plano requestRecordToPlano(PlanoRequestDTO planoRequestDto)
    {
        Plano plano = new Plano();

        plano.setNome(planoRequestDto.nome());
        plano.setServicos(planoRequestDto.servicos());
        plano.setPreco(planoRequestDto.preco());
        return plano;
    }

    public PlanoResponseDTO planoToResponseDto(Plano plano)
    {
        return new PlanoResponseDTO(
                plano.getId(),
                plano.getNome(),
                plano.getServicos(),
                plano.getPreco()
        );
    }
}
