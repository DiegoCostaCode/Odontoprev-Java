package com.example.Odontoprev_Java.DTO.doutor;

import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public record DoutorRequestDTO
        (
                @NotBlank(message = "Nome é obrigatório")
                @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
                String nome,

                @NotBlank(message = "Data de nascimento é obrigatória")
                @Size(min = 6, max = 6, message = "CRM deve ter 6 caracteres")
                String CRM,

                @NotBlank(message = "CPF é obrigatório")
                @CPF(message = "CPF inválido")
                String CPF
        )
        {

}
