package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinica.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.clinica.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.service.ClinicaMapper;
import com.example.Odontoprev_Java.service.procedures.ClinicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/clinica", produces = {"aplication/json"})
@Tag(name = "api-clinicas")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private ClinicaMapper clinicaMapper;

    @Autowired
    private ClinicaService clinicaService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> createClinica(@Valid @RequestBody ClinicaRequestDTO clinicaRequestDTO)
    {
        Clinica clinicaConvertida = clinicaMapper.requestToClinica(clinicaRequestDTO);
        Clinica clinicaCriada = clinicaRepository.save(clinicaConvertida);
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponse(clinicaCriada);
        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> readClinicaById(@PathVariable Long id) {
        Optional<Clinica> clinicaSalva = clinicaRepository.findById(id);
        if (clinicaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponse(clinicaSalva.get());

        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.OK);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<ClinicaResponseDTO>>> readClinicas() {
    List<Clinica> clinicas = clinicaRepository.findAll();
    List<EntityModel<ClinicaResponseDTO>> clinicasResponse = clinicas.stream()
            .map(clinica -> {
                ClinicaResponseDTO clinicaResponse = clinicaMapper.clinicaToResponse(clinica);
                return EntityModel.of(clinicaResponse,
                        linkTo(methodOn(ClinicaController.class).readClinicas()).withSelfRel(),
                        linkTo(methodOn(ClinicaController.class).createClinica(null)).withRel("post"),
                        linkTo(methodOn(ClinicaController.class).readClinicaById(clinica.getId())).withRel("get"),
                        linkTo(methodOn(ClinicaController.class).updateClinica(clinica.getId(), null)).withRel("put"),
                        linkTo(methodOn(ClinicaController.class).deleteClinica(clinica.getId())).withRel("delete"));
            })
            .collect(Collectors.toList());
    return new ResponseEntity<>(clinicasResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> updateClinica(@PathVariable Long idClinica, @Valid @RequestBody ClinicaRequestDTO clinicaRequestDTO) {
        Optional<Clinica> clinicaSalva = clinicaRepository.findById(idClinica);
        if (clinicaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Clinica clinicaConvertida = clinicaMapper.requestToClinica(clinicaRequestDTO);
        clinicaConvertida.setId(idClinica);
        Clinica clinicaAtualizada = clinicaRepository.save(clinicaConvertida);
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponse(clinicaAtualizada);

        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> deleteClinica(@PathVariable Long idClinica) {
        Optional<Clinica> clinicaSalva = clinicaRepository.findById(idClinica);
        if (clinicaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        clinicaRepository.deleteById(idClinica);
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponse(clinicaSalva.get());

        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.OK);
    }

    //Endpoints criados apenas para a matéria de banco de dados!

    @PostMapping(value = "/procedure", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> createOdontoClinica(@Valid @RequestBody ClinicaRequestDTO clinicaRequestDTO) {

        ClinicaResponseDTO clinicaResponse = clinicaService.inserirOdontoClinica(clinicaRequestDTO);
        return new ResponseEntity<>(clinicaResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/procedure/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> updateOdontoPaciente(@PathVariable Long idClinica, @Valid @RequestBody ClinicaRequestDTO clinicaRequestDTO) {
        ClinicaResponseDTO clinicaResponse = clinicaService.updateOdontoClinica(clinicaRequestDTO, idClinica);
        return new ResponseEntity<>(clinicaResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/procedure/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteOdontoClinica(@PathVariable Long idClinica) {
        clinicaService.deleteOdontoClinica(idClinica);
        return new ResponseEntity<>("Clinica deletado com sucesso!", HttpStatus.OK);
    }

}
