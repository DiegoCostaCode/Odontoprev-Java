package com.example.Odontoprev_Java.DTO.auditorDTO;

import com.example.Odontoprev_Java.Model.usuario.Enum_tipo_usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditorRequestDTO {
    @NotBlank(message = "O nome de auditor é obrigatório!")
    @Size(min = 3, max=30, message="A razão social deve ter entre 3 e 30 caracteres")
    private String nome;

    @NotBlank(message = "Email do representante é obrigatório")
    @Email(message = "Email em inválido, siga o formato: exemplo@email.com.")
    private String email;

    @NotBlank(message = "Telefone deve ser preenchido")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$",
            message = "O telefone deve estar no formato (DDD) 91234-5678 ou (DDD) 1234-5678")
    private String telefone;

    @NotBlank(message = "Não foi definido uma senha para a clínica")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{6,}$",
            message = "A senha deve ter pelo menos 6 caracteres, incluindo pelo menos uma letra minúscula e um caractere especial."
    )
    private String senha;

    private final Enum_tipo_usuario tipo = Enum_tipo_usuario.AUDITOR;
}
