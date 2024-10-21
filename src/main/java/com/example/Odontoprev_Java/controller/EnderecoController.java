package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
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

    @Autowired
    private ClinicaRepository clinicaRepository;

    @PostMapping(value = "/usuario/{paciente_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> UsuarioAddEndereco(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long paciente_id)
    {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(paciente_id);
        if (pacienteEncontrado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoResponseDTO(enderecoCriada);

        Paciente paciente = pacienteEncontrado.get();
        paciente.setEndereco(enderecoCriada);
        pacienteRepository.save(paciente);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/usuario-atualizar/{paciente_id}/{endereco_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> UsuarioUpdateEndereco(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long paciente_id,  @PathVariable Long endereco_id)
    {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(paciente_id);
        if (pacienteEncontrado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(endereco_id);
        if (enderecoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoResponseDTO(enderecoCriada);

        Paciente paciente = pacienteEncontrado.get();
        paciente.setEndereco(enderecoCriada);
        pacienteRepository.save(paciente);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }


    @PostMapping(value = "/clinica/{clinica_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> ClinicaAddEndereco(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO,
            @PathVariable Long clinica_id)
    {
        Optional<Clinica> clinicaEncontrada = clinicaRepository.findById(clinica_id);
        if (clinicaEncontrada.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Endereco enderecoConvertida = enderecoMapper.requestToEndereco(enderecoRequestDTO);
        Endereco enderecoCriada = enderecoRepository.save(enderecoConvertida);
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoResponseDTO(enderecoCriada);

        Clinica clinica = clinicaEncontrada.get();
        clinica.setEndereco(enderecoCriada);
        clinicaRepository.save(clinica);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }



    @PutMapping(value = "/clinica-atualizar/{clinica_id}/{endereco_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoResponseDTO> ClinicaUpdateEndereco(
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
        EnderecoResponseDTO enderecoResponseDto = enderecoMapper.enderecoResponseDTO(enderecoCriada);

        Clinica clinica = clinicaEncontrada.get();
        clinica.setEndereco(enderecoCriada);
        clinicaRepository.save(clinica);

        return new ResponseEntity<>(enderecoResponseDto, HttpStatus.CREATED);
    }
}
