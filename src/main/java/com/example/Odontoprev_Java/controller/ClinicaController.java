package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.service.ClinicaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clinica", produces = {"aplication/json"})
@Tag(name = "api-clinicas")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;
    @Autowired(required = true)
    private ClinicaMapper clinicaMapper;

    Pageable paginacao = PageRequest.of(0, 2, Sort.by("servico").descending());

    @Operation(summary = "Registra clínicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clínica registrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<ClinicaResponseDTO> createClinica(@Valid @RequestBody ClinicaRequestDTO clinicaRequestDTO)
    {
        Clinica clinicaConvertida = clinicaMapper.requestRecordToClinica(clinicaRequestDTO);
        Clinica clinicaCriada = clinicaRepository.save(clinicaConvertida);
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponseDto(clinicaCriada);
        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.CREATED);
    }
}
