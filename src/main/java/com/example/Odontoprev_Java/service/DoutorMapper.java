package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.DoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.DoutorResponseDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Plano;
import org.springframework.stereotype.Service;

@Service
public class DoutorMapper {

    public Doutor requestRecordToDoutor(DoutorRequestDTO doutorRequestDto)
    {
        Doutor doutor = new Doutor();

        doutor.setNome(doutorRequestDto.nome());
        doutor.setCpf(doutorRequestDto.cpf());
        doutor.setCro(doutorRequestDto.cro());
        doutor.setDataNascimento(doutorRequestDto.dataNascimento());
        doutor.setEmail(doutorRequestDto.email());
        doutor.setTelefone(doutorRequestDto.telefone());
        doutor.setCpf(doutorRequestDto.cpf());
        doutor.setEndereco(doutorRequestDto.endereco());

        return doutor;
    }

    public DoutorResponseDTO doutorToResponseDto(Doutor doutor)
    {
        return new DoutorResponseDTO(
                doutor.getId(),
                doutor.getNome(),
                doutor.getCpf(),
                doutor.getCro(),
                doutor.getEmail(),
                doutor.getDataNascimento(),
                doutor.getTelefone(),
                doutor.getEndereco()
        );
    }
}
