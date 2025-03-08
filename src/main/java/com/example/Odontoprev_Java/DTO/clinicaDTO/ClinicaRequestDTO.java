package com.example.Odontoprev_Java.DTO.clinicaDTO;

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
public class ClinicaRequestDTO {

    @NotBlank(message = "A razão social deve ser preenchida!")
    @Size(min = 3, max=30, message="A razão social deve ter entre 3 e 30 caracteres")
    private String razaosocial;

    @NotBlank(message = "O CNPJ da clínica deve ser preenchido!")
    @Size(min = 14, message="O CPNJ contém menos de 14 digitos")
    @Pattern(regexp = "^(\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2})",
            message = "Formato de CNPJ inválido. Utilize o padrão: 12.345.678/9000-01.")
    private String cnpj;

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

}
