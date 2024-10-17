package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import com.example.Odontoprev_Java.Model.Paciente;
import io.swagger.v3.oas.annotations.servers.Server;
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

    public EnderecoResponseDTO enderecoResponseDTO(Endereco endereco)
    {
        return new EnderecoResponseDTO(
                endereco.getId_endereco(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCEP(),
                endereco.getCidade()
        );
    }
}
