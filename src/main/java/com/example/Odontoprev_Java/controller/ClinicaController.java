package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.*;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Enums.Enum_tipo_servico;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.Repository.DoutorRepository;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clinica", produces = {"aplication/json"})
@Tag(name = "api-clinicas")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DoutorRepository doutorRepository;

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

    @PutMapping("/doutor")
    public ResponseEntity<Void> addDoutorClinica(@Valid @RequestBody ClinicaDoutorRequest clinicaDoutorRequest) {
        Optional<Clinica> clinica = clinicaRepository.findById(clinicaDoutorRequest.idClinica());
        Optional<Doutor> doutor = doutorRepository.findById(clinicaDoutorRequest.idDoutor());
        if (clinica.isEmpty() || doutor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        clinica.get().getDoutores().add(doutor.get());
        clinicaRepository.save(clinica.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/servicos")
    public ResponseEntity<Void> addServicosClinicas(@Valid @RequestBody ClinicaServicoRequest clinicaServicoRequest) {
        Optional<Clinica> clinicaOptional = clinicaRepository.findById(clinicaServicoRequest.idClinica());

        if (clinicaOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Clinica clinica = clinicaOptional.get();

        // Adiciona os serviços da solicitação à clínica
        List<Enum_tipo_servico> novosServicos = clinicaServicoRequest.servicos();
        for (Enum_tipo_servico servico : novosServicos) {
            if (!clinica.getServicos().contains(servico)) {
                clinica.getServicos().add(servico); // Adiciona o serviço se não estiver na lista
            }
        }

        clinicaRepository.save(clinica);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Clinica>> readClinica() {
        List<Clinica> clinicas = clinicaRepository.findAll();
        if (clinicas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clinicas, HttpStatus.OK);
    }
}
