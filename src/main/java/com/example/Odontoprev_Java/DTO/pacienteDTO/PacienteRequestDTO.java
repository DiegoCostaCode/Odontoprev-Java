package com.example.Odontoprev_Java.DTO.pacienteDTO;

import com.example.Odontoprev_Java.model.usuario.Enum_tipo_usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max=30, message="A nome deve ter entre 3 e 30 caracteres")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$",
            message = "O telefone deve estar no formato (DDD) 91234-5678 ou (DDD) 1234-5678")
    private String telefone;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento inválida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank(message = "Não foi definido uma senha para a clínica")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{6,}$",
            message = "A senha deve ter pelo menos 6 caracteres, incluindo pelo menos uma letra minúscula e um caractere especial."
    )
    private String senha;

    @NotNull(message = "Você deve escolher um plano!")
    private Long id_plano;

    private final Enum_tipo_usuario tipo = Enum_tipo_usuario.PACIENTE;
}