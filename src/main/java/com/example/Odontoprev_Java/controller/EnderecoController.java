package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Repository.EnderecoRepository;
import com.example.Odontoprev_Java.Repository.PacienteRepository;
import com.example.Odontoprev_Java.service.EnderecoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/endereco", produces = {"aplication/json"})
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping(value = "/adicionar/{paciente_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> createUsuario(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long paciente_id)
    {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(paciente_id);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoResponseDTO(enderecoCriada);

        Paciente paciente = pacienteSalvo.get();
        paciente.setEndereco(enderecoCriada);
        pacienteRepository.save(paciente);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/adicionar/{paciente_id}/{endereco_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> createUsuario(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long paciente_id,  @PathVariable Long endereco_id)
    {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(paciente_id);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(endereco_id);
        if (enderecoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoResponseDTO(enderecoCriada);

        Paciente paciente = pacienteSalvo.get();
        paciente.setEndereco(enderecoCriada);
        pacienteRepository.save(paciente);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }
}
