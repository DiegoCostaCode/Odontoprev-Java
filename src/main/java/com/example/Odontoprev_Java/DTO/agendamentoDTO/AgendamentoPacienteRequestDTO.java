package com.example.Odontoprev_Java.DTO.agendamentoDTO;

import com.example.Odontoprev_Java.model.agendamento.Enum_status_agendamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoPacienteRequestDTO {

    @NotNull(message = "Data de nascimento é obrigatória")
    @Future(message = "Data de agendamento inválida")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAgendamento;

    @NotNull(message = "Um paciente válido deve ser selecionado")
    private Long pacienteId;

    @NotNull(message = "Uma clínica válido deve ser selecionada")
    private Long clinicaId;

    @NotNull(message = "Um procedimento deve ser selecionado")
    private Long procedimentoId;

    private Enum_status_agendamento status = Enum_status_agendamento.MARCADA;
}
