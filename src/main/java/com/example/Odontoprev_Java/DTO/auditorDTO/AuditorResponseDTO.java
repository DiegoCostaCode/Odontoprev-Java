package com.example.Odontoprev_Java.DTO.auditorDTO;

public record AuditorResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        Long usuario_id
){
}
