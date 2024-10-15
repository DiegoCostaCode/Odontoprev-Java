package com.example.Odontoprev_Java.DTO.clinica;

import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;

import java.util.List;

public record ClinicaResponseDTO(
        Long id,
        String razaoSocial,
        List<Enum_tipo_servico> servicos,
        String emailRepresentante,
        Endereco endereco,
        List<Doutor> doutores
){}
