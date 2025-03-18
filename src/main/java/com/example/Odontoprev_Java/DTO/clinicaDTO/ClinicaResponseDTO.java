package com.example.Odontoprev_Java.DTO.clinicaDTO;


public record ClinicaResponseDTO(
        Long id,
        String razaosocial,
        String cnpj,
        String email,
        String telefone,
        Long usuario_id
){
}
