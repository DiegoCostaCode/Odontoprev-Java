package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Repository.PlanoRepository;
import com.example.Odontoprev_Java.service.PlanoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/plano", produces = {"aplication/json"})
@Tag(name = "api-planos")
public class PlanoController {

    @Autowired
    private PlanoRepository planoRepository;
    @Autowired(required = true)
    private PlanoMapper planoMapper;


    @Operation(summary = "Registra um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plano registrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PlanoResponseDTO> createUsuario(@Valid @RequestBody PlanoRequestDTO planoRequestDTO)
    {
        Plano planoConvertida = planoMapper.requestRecordToPlano(planoRequestDTO);
        Plano planoCriada = planoRepository.save(planoConvertida);
        PlanoResponseDTO planoResponseDTO = planoMapper.planoToResponseDto(planoCriada);
        return new ResponseEntity<>(planoResponseDTO, HttpStatus.CREATED);
    }
}
