package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.Repository.EnderecoRepository;
import com.example.Odontoprev_Java.Repository.PacienteRepository;
import com.example.Odontoprev_Java.service.EnderecoMapper;
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
@RequestMapping(value = "/endereco", produces = {"aplication/json"})
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @PostMapping(value = "/paciente/{idPaciente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> PacienteAddEndereco(@PathVariable Long idPaciente,
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO)
    {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Endereco endereco = enderecoMapper.requestToEndereco(enderecoRequestDTO);

        Endereco enderecoRegistrado = enderecoRepository.save(endereco);

        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoToResponse(enderecoRegistrado);

        paciente.setEndereco(enderecoRegistrado);
        pacienteRepository.save(paciente);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/clinica/{IdClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> clinicaAddEndereco(@PathVariable Long IdClinica,
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO)
    {
        Optional<Clinica> clinicaEncontrada = clinicaRepository.findById(IdClinica);
        if (clinicaEncontrada.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoToResponse(enderecoCriada);

        Clinica clinica = clinicaEncontrada.get();
        clinica.setEndereco(enderecoCriada);
        clinicaRepository.save(clinica);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/paciente/{idPaciente}/{idEndereco}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> pacienteUpdateEndereco(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long idPaciente,  @PathVariable Long idEndereco)
    {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(idPaciente);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(idEndereco);
        if (enderecoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoToResponse(enderecoCriada);

        Paciente paciente = pacienteSalvo.get();
        paciente.setEndereco(enderecoCriada);
        pacienteRepository.save(paciente);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }


    @PutMapping(value = "/clinica/{clinica_id}/{endereco_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> clinicaUpdateEndereco(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long clinica_id,  @PathVariable Long endereco_id)
    {
        Optional<Clinica> clinicaEncontrada = clinicaRepository.findById(clinica_id);
        if (clinicaEncontrada.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(endereco_id);
        if (enderecoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoToResponse(enderecoCriada);

        Clinica clinica = clinicaEncontrada.get();
        clinica.setEndereco(enderecoCriada);
        clinicaRepository.save(clinica);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<EnderecoResponseDTO>>> readEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<EntityModel<EnderecoResponseDTO>> enderecosResponse = enderecos.stream()
                .map(endereco -> {
                    EnderecoResponseDTO enderecoResponse = enderecoMapper.enderecoToResponse(endereco);

                    return EntityModel.of(enderecoResponse,
                            linkTo(methodOn(EnderecoController.class).readEnderecos()).withSelfRel(),

                            linkTo(methodOn(EnderecoController.class).clinicaAddEndereco(null, null)).withRel("post"),
                            linkTo(methodOn(EnderecoController.class).PacienteAddEndereco(null, null)).withRel("post"),
                            linkTo(methodOn(EnderecoController.class).pacienteUpdateEndereco(null,null,endereco.getId())).withRel("update"),
                            linkTo(methodOn(EnderecoController.class).clinicaUpdateEndereco(null,null,endereco.getId())).withRel("update"),
                            linkTo(methodOn(EnderecoController.class).deleteEndereco(endereco.getId())).withRel("delete"));
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(enderecosResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idEndereco}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> deleteEndereco(@PathVariable Long idEndereco)
    {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RuntimeException("Endereco não encontrado"));
        enderecoRepository.delete(endereco);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }









}
