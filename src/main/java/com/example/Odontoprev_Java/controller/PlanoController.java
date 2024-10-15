package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.plano.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.plano.PlanoResponseDTO;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/plano", produces = {"aplication/json"})
@Tag(name = "api-planos")
public class PlanoController {

    @Autowired
    private PlanoRepository planoRepository;
    @Autowired(required = true)
    private PlanoMapper planoMapper;


    @Operation(summary = "Cria o plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping(value = "/ativar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanoResponseDTO> planoSalvoo(@Valid @RequestBody PlanoRequestDTO planoRequestDTO)
    {
        Plano planoConvertido = planoMapper.requestRecordToPlano(planoRequestDTO);
        Plano planoCriado = planoRepository.save(planoConvertido);
        PlanoResponseDTO planoResponseDto = planoMapper.planoToResponseDto(planoCriado);
        return new ResponseEntity<>(planoResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Atendimento planos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planos encontradas"),
            @ApiResponse(responseCode = "404", description = "Planos não encontrados"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanoResponseDTO>> readPlanos() {
        // Busca todos os planos
        List<Plano> planos = planoRepository.findAll();

        // Verifica se a lista está vazia
        if (planos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Converte a lista de Planos para PlanoResponseDTO
        List<PlanoResponseDTO> planosDTO = planos.stream()
                .map(plano -> planoMapper.planoToResponseDto(plano))
                .collect(Collectors.toList());

        // Retorna a lista de DTOs com status OK
        return new ResponseEntity<>(planosDTO, HttpStatus.OK);
    }
}
