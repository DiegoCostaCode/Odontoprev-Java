package com.example.Odontoprev_Java.DTO.doutor;

import com.example.Odontoprev_Java.Model.ClinicaDoutor;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;

import java.time.LocalDate;
import java.util.List;

public record DoutorResponseDTO(
        Long id,
        String nome,
        String CRM,
        String CPF,
        List<ClinicaDoutor> clinicaDotores
        ) {
}
