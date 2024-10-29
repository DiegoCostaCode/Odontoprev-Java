package com.example.Odontoprev_Java.DTO.paciente;

import com.example.Odontoprev_Java.Model.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public record PacienteRequestDTO(

        @NotBlank
        @Size(min = 3, max = 50, message = "O nome deve ter no mínimo 3 e no máximo 10 caracteres")
        String nome,
        @NotBlank
        @CPF(message="CPF inválido")
        String cpf,
        @NotNull
        @Past(message = "A data de nascimento deve ser no passado.")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        Date dataNascimento,
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message="E-mail inválido")
         String email,
        @NotBlank
        @Pattern(
                regexp = "^\\(?[0]?[1-9]{2}\\)?[\\s-]?[9]?[1-9]\\d{3}[-\\s]?\\d{4}",
                message = "Insira um telefone válido!")
        String telefone
){ }
