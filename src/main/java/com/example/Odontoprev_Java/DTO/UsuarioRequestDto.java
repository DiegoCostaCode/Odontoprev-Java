package com.example.Odontoprev_Java.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class UsuarioRequestDto {

    @NotBlank
    @Size(min = 3, max = 50, message = "O nome deve ter no mínimo 3 e no máximo 10 caracteres")
    private String nome;
    @NotBlank
    @CPF(message="CPF inválido")
    private String cpf;
    @NotBlank
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message="E-mail inválido")
    private String email;
    @NotBlank
    @Pattern(
            regexp = "\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/",
            message = "Insira um telefone válido!")
    private String telefone;

}
