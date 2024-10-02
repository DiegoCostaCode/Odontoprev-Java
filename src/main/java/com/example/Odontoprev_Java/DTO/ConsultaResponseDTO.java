package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import com.example.Odontoprev_Java.Model.Usuario;

import java.time.LocalDate;

public record ConsultaResponseDTO(
        Long id,
        LocalDate agendamento,
        Enum_tipo_servico servico,
        Usuario usuario,
        Doutor doutor,
        Clinica clinica,
        String observacoes
) {
}