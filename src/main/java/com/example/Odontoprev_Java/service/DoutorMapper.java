package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.doutor.DoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.doutor.DoutorResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import org.springframework.stereotype.Service;

@Service
public class DoutorMapper {

    public Doutor doutorToRequest(DoutorRequestDTO doutorRequestDTO)
    {
        Doutor doutor = new Doutor();

        doutor.setNome(doutorRequestDTO.nome());
        doutor.setCRM(doutorRequestDTO.CRM());
        doutor.setCPF(doutorRequestDTO.CPF());

        return doutor;
    }

    public DoutorResponseDTO doutorToResponse(Doutor doutor)
    {
        return new DoutorResponseDTO(
                doutor.getId(),
                doutor.getNome(),
                doutor.getCRM(),
                doutor.getCPF()
        );
    }
}
