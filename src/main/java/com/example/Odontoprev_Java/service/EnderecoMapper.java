package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.BairroResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.Model.Bairro;
import com.example.Odontoprev_Java.Model.Endereco;
import com.example.Odontoprev_Java.Model.Plano;

public class EnderecoMapper {
    //Record para Plano
    public Endereco requestRecordToPlano(EnderecoRequestDTO enderecoRequestDTO)
    {
        Endereco endereco = new Endereco();

        endereco.setRua(enderecoRequestDTO.rua());
        endereco.setNumero(enderecoRequestDTO.numero());
        endereco.setBairro(enderecoRequestDTO.bairro());
        endereco.setCep(enderecoRequestDTO.cep());
        return endereco;
    }

    public EnderecoResponseDTO planoToResponseDto(Endereco endereco)
    {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getBairro()
        );
    }
}
