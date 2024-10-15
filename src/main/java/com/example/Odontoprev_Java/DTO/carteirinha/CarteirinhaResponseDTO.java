package com.example.Odontoprev_Java.DTO.carteirinha;

import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.Paciente;

import java.util.Date;
import java.util.UUID;

public record CarteirinhaResponseDTO
        (
                UUID id,
                Date date,
                Date validade,
                Paciente paciente,
                Plano plano
        ) {

}
