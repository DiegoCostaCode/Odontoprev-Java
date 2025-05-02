package com.example.Odontoprev_Java.DTO.agendamentoDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AgendamentoResponseDTO
        (
                @JsonProperty("id")
                Long id,
                @JsonProperty("dataAgendamento")
                LocalDateTime dataAgendamento,
                @JsonProperty("finalizadoEm")
                LocalDateTime finalizadoEm,
                @JsonProperty("status")
                String status,
                @JsonProperty("paciente")
                String paciente,
                @JsonProperty("clinica")
                String clinica,
                @JsonProperty("precoAtendimento")
                Double precoAtendimento,
                @JsonProperty("descricaoAtendimento")
                String descricaoAtendimento,
                @JsonProperty("procedimento")
                String procedimento,
                @JsonProperty("descricaoProcedimento")
                String descricaoProcedimento,
                @JsonProperty("procedimentoCobertura")
                Double procedimentoCobertura,
                @JsonProperty("atendimentoValor")
                Double atendimentoValor
        ) implements Serializable {
}
