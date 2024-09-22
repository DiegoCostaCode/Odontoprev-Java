package com.example.Odontoprev_Java.DTO;

import com.example.Odontoprev_Java.Model.Servicos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

public record PlanoRequestDTO (

    @NotBlank
    String nome,

    @NotNull
     List<Servicos> servicos,

    @NotNull
     double preco

    ){
}
