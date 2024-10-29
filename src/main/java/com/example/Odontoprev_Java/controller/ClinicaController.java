package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinica.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.clinica.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.doutor.DoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.Repository.DoutorRepository;
import com.example.Odontoprev_Java.service.ClinicaMapper;
import com.example.Odontoprev_Java.service.DoutorMapper;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/clinica", produces = {"aplication/json"})
@Tag(name = "api-clinicas")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired(required = true)
    private ClinicaMapper clinicaMapper;


    @PostMapping(value = "/cadastro",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> createClinica(@Valid @RequestBody ClinicaRequestDTO clinicaRequestDTO)
    {
        Clinica clinicaConvertida = clinicaMapper.requestToClinica(clinicaRequestDTO);
        Clinica clinicaCriada = clinicaRepository.save(clinicaConvertida);
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponse(clinicaCriada);
        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> readClinica(@PathVariable Long id) {
        Optional<Clinica> clinicaSalva = clinicaRepository.findById(id);
        if (clinicaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponse(clinicaSalva.get());

        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.OK);
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


}
