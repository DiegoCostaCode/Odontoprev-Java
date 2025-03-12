package com.example.Odontoprev_Java.DTO.pacienteDTO;

import com.example.Odontoprev_Java.Model.usuario.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotBlank(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento inválida")
    private String dataNascimento;
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
