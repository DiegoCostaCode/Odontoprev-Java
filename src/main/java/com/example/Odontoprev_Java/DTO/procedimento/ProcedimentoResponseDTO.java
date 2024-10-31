package com.example.Odontoprev_Java.DTO.procedimento;

import com.example.Odontoprev_Java.Model.Procedimento.Enum_procedimento;

public record ProcedimentoResponseDTO
        (
                Long id,
                Enum_procedimento descricao
        ){
}
