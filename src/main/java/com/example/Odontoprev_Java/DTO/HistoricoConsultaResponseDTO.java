package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Consulta;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Usuario;

import java.util.List;

public record HistoricoConsultaResponseDTO
        (
                Long id,
               Usuario usuario,
               Clinica clinica,
               Doutor doutor,
               List<Consulta> consultas
        ) {
}
