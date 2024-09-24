package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Consulta;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record HistoricoConsultaRequestDTO(
        @NotBlank
        Usuario usuario,
        @NotBlank
        Clinica clinica,
        @NotBlank
        Doutor doutor,
        @NotNull
        List<Consulta> consultas
        ) {
}
