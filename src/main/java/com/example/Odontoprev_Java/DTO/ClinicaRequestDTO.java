package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Endereco;
import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.util.List;
import java.util.Set;

public record ClinicaRequestDTO
        (
                @NotBlank
                String razaoSocial,
                @NotBlank
                String emailRepresentante,
                @NotBlank
                Endereco endereco
        ) {

}