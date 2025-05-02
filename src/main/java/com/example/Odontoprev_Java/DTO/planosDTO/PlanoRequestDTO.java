package com.example.Odontoprev_Java.DTO.planosDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanoRequestDTO {

    @NotBlank(message = "Nomeie o plano!")
    @Size(min = 5, max = 30, message = "O nome do procedimento deve ter entre 5 e 30 caracteres")
    private String nome;

    @NotBlank(message = "Dê uma descrição ao plano!")
    @Size(min = 10, max = 100, message = "A descrição deve ter entre 10 e 100 caracteres")
    private String descricao;

    @NotNull(message = "Defina um preço para o plano!")
    @Positive(message = "O preço deve ser um número positivo")
    @DecimalMin(value = "0.01", inclusive = true, message = "O preço deve ser maior que zero")
    private double preco;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    private String ativo = "F";
}
