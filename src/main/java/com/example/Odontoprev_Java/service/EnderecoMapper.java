package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;

public class EnderecoMapper {
    //Record para Plano
    public Endereco requestRecordToPlano(EnderecoRequestDTO enderecoRequestDTO)
    {
        Endereco endereco = new Endereco();

        endereco.setRua(enderecoRequestDTO.rua());
        endereco.setNumero(enderecoRequestDTO.numero());
        endereco.setCep(enderecoRequestDTO.cep());
        endereco.setBairro(enderecoRequestDTO.bairro());
        endereco.setCidade(enderecoRequestDTO.cidade());
        endereco.setEstado(enderecoRequestDTO.estado());
        endereco.setPais(enderecoRequestDTO.pais());
        return endereco;
    }

    public EnderecoResponseDTO enderecoResponseDTO(Endereco endereco)
    {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getPais()
        );
    }
}
