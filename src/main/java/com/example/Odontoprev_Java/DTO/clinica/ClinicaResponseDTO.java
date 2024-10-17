package com.example.Odontoprev_Java.DTO.clinica;

import com.example.Odontoprev_Java.Model.Atendimento;
import com.example.Odontoprev_Java.Model.ClinicaDoutor;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;

import java.util.List;

public record ClinicaResponseDTO(
        Long id,
        String razaoSocial,
        String descricao,
        String cnpj,
        String emailRepresentante,
        Endereco endereco,
        List<ClinicaDoutor> doutores,
        List<Atendimento> atendimentos
){}
