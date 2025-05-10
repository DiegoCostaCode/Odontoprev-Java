package com.example.Odontoprev_Java.DTO.agendamentoDTO;

import com.example.Odontoprev_Java.model.agendamento.Enum_status_agendamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoClinicaRequestDTO {

    @NotNull(message = "Data de nascimento inválida")
    @PositiveOrZero(message = "Preço do atendimento deve ser maior ou igual a zero")
    private double precoAtendimento = 0.0;

    @NotNull(message = "Descrição do atendimento é obrigatória")
    @Size(min=10, max=300, message="A descrição deve ter entre 10 e 100 caracteres")
    private String descricaoAtendimento = "";

    private LocalDateTime finalizadoEm = LocalDateTime.now();

    private Enum_status_agendamento status = Enum_status_agendamento.FINALIZADA;

}
