package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.paciente.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.*;
import com.example.Odontoprev_Java.Repository.*;
import com.example.Odontoprev_Java.service.PacienteMapper;
import com.example.Odontoprev_Java.service.procedures.PacienteService;
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
@RequestMapping(value = "/paciente", produces = {"aplication/json"})
@Tag(name = "api-usuarios")
public class PacienteController {

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping(value = "/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO>readPaciente(@PathVariable Long idPaciente) {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(idPaciente);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        PacienteResponseDTO pacienteResponse = pacienteMapper.pacienteResponseDTO(pacienteSalvo.get());

        return new ResponseEntity<>(pacienteResponse, HttpStatus.OK);
    };

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<PacienteResponseDTO>>> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<EntityModel<PacienteResponseDTO>> pacientesResponse = pacientes.stream()
                .map(paciente -> {
                    PacienteResponseDTO pacienteResponse = pacienteMapper.pacienteResponseDTO(paciente);
                    return EntityModel.of(pacienteResponse,
                            linkTo(methodOn(PacienteController.class).readPaciente(paciente.getId())).withSelfRel(),
                            linkTo(methodOn(PacienteController.class).createPaciente(null)).withRel("post"),
                            linkTo(methodOn(PacienteController.class).deleteUsuario(paciente.getId())).withRel("delete"),
                            linkTo(methodOn(PacienteController.class).updatePaciente(paciente.getId(), null)).withRel("update"));
                })
                .collect(Collectors.toList());
    return new ResponseEntity<>(pacientesResponse, HttpStatus.OK);
}

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> createPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequest)
    {
        Paciente pacienteConvertida = pacienteMapper.requestToPlano(pacienteRequest);
        Paciente pacienteCriada = pacienteRepository.save(pacienteConvertida);
        PacienteResponseDTO pacienteResponseDto = pacienteMapper.pacienteResponseDTO(pacienteCriada);
        return new ResponseEntity<>(pacienteResponseDto, HttpStatus.CREATED);

    }

    @PutMapping(value = "/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> updatePaciente(@PathVariable Long idPaciente, @Valid @RequestBody PacienteRequestDTO pacienteRequest) {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(idPaciente);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Paciente pacienteConvertida = pacienteMapper.requestToPlano(pacienteRequest);
        pacienteConvertida.setId(idPaciente);
        Paciente pacienteAtualizada = pacienteRepository.save(pacienteConvertida);
        PacienteResponseDTO pacienteResponse = pacienteMapper.pacienteResponseDTO(pacienteAtualizada);
        return new ResponseEntity<>(pacienteResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> deleteUsuario(@PathVariable Long idPaciente) {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(idPaciente);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        pacienteRepository.deleteById(idPaciente);
        PacienteResponseDTO pacienteResponse = pacienteMapper.pacienteResponseDTO(pacienteSalvo.get());
        return new ResponseEntity<>(pacienteResponse, HttpStatus.OK);
    }

    //Endpoints criados apenas para a matéria de banco de dados!

    @PostMapping(value = "/procedure", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> createOdontoPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        PacienteResponseDTO pacienteResponse = pacienteService.inserirOdontoPaciente(pacienteRequestDTO);
        return new ResponseEntity<>(pacienteResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/procedure/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> updateOdontoPaciente(@PathVariable Long idPaciente, @Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        PacienteResponseDTO pacienteResponse = pacienteService.updateOdontoPaciente(pacienteRequestDTO, idPaciente);
        return new ResponseEntity<>(pacienteResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/procedure/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteOdontoPaciente(@PathVariable Long idPaciente) {
        pacienteService.deleteOdontoPaciente(idPaciente);
        return new ResponseEntity<>("Paciente deletado com sucesso!", HttpStatus.OK);
    }

}

