package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Endereco;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;


public record DoutorRequestDTO
        (
                @NotBlank
                @Size(min = 3, max = 50, message = "O nome deve ter no mínimo 3 e no máximo 10 caracteres")
                String nome,
                @NotBlank
                @CPF(message="CPF inválido.")
                String cpf,
                @NotBlank
                @Pattern(regexp = "\\d{5}",message="CRO inválido.")
                String cro,
                @NotBlank
                Date dataNascimento,
                @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message="E-mail inválido")
                String email,
                @NotNull
                @Pattern(
                        regexp = "\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/",
                        message = "Insira um telefone válido!")
                String telefone,
                @NotBlank
                Endereco endereco
        )
        {

}
