package com.example.Odontoprev_Java.DTO.procedimentoDTO;

import com.example.Odontoprev_Java.model.procedimento.Enum_procedimento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimentoRequestDTO {

    @NotNull(message = "Campo procedimento é obrigatório")
    private Enum_procedimento titulo;

    @NotNull(message = "Defina um preço para o plano!")
    @Positive(message = "O preço deve ser um número positivo")
    @DecimalMin(value = "0.01", inclusive = true, message = "O preço deve ser maior que zero")
    private double valorCobertura;

    @NotNull(message = "Campo descrição é obrigatório")
    @Size(min=10, max=300, message="A descrição deve ter entre 10 e 300 caracteres")
    private String descricao;

    private String status = "F";
}
