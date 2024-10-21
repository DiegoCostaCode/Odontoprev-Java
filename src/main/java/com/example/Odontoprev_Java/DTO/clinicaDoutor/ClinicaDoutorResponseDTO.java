package com.example.Odontoprev_Java.DTO.clinicaDoutor;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;

import java.time.LocalDate;

public record ClinicaDoutorResponseDTO
        (
                Long id,
                Clinica clinica,
                Doutor doutor,
                LocalDate dataRelacionamento,
                LocalDate dataFimRelacionamento
        ) {

}
