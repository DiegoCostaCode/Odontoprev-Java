package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.endereco.BairroRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.BairroResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.Model.Bairro;
import com.example.Odontoprev_Java.Model.Endereco;

public class BairroMapper {


    public Bairro requestRecordToBairro(BairroRequestDTO bairroRequestDTO)
    {
        Bairro bairro = new Bairro();

        bairro.setBairro(bairroRequestDTO.bairro());
        bairro.setCidade(bairroRequestDTO.cidade());
        return bairro;
    }

    public BairroResponseDTO bairroToResponseDto(Bairro bairro)
    {
        return new BairroResponseDTO(
                bairro.getId(),
                bairro.getBairro(),
                bairro.getCidade()
        );
    }
}
