package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import org.springframework.stereotype.Service;

@Service
public class EnderecoMapper {

    public Endereco requestToEndereco(EnderecoRequestDTO enderecoRequestDTO)
    {
        Endereco endereco = new Endereco();

        endereco.setRua(enderecoRequestDTO.rua());
        endereco.setNumero(enderecoRequestDTO.numero());
        endereco.setCEP(enderecoRequestDTO.cep());
        endereco.setComplemento(enderecoRequestDTO.complemento());
        endereco.setCidade(enderecoRequestDTO.cidade());

        return endereco;
    }

    public EnderecoResponseDTO enderecoToResponse(Endereco endereco)
    {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCEP(),
                endereco.getCidade()
        );
    }
}
