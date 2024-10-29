package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorResponseDTO;
import com.example.Odontoprev_Java.DTO.procedimento.ProdecimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.ClinicaDoutor;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Repository.ClinicaDoutorRepository;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.Repository.DoutorRepository;
import com.example.Odontoprev_Java.service.ClinicaDoutorMapper;
import com.example.Odontoprev_Java.service.ClinicaMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cadastro-relacionamento")
public class ClinicaDoutorController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private ClinicaDoutorRepository clinicaDoutorRepository;

    @Autowired
    private ClinicaDoutorMapper clinicaDoutorMapper;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDoutor> createClinicaDoutor( @Valid @RequestBody ClinicaDoutorRequestDTO clinicaDoutorRequestDTO) {

        Clinica clinica = clinicaRepository.findById(clinicaDoutorRequestDTO.clinicaId().getId())
                .orElseThrow(() -> new RuntimeException("Clinica não encontrado"));

        Doutor doutor = doutorRepository.findById(clinicaDoutorRequestDTO.doutorId().getId())
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        ClinicaDoutor clinicaDoutor = new ClinicaDoutor();
        clinicaDoutor.setClinica(clinica);
        clinicaDoutor.setDoutor(doutor);
        clinicaDoutor.setDataRelacionamento(clinicaDoutorRequestDTO.dataRelacionamento());
        clinicaDoutor.setDataFimRelacionamento(clinicaDoutorRequestDTO.dataFimRelacionamento());

        clinicaDoutorRepository.save(clinicaDoutor);

        return new ResponseEntity<>(clinicaDoutor, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClinicaDoutorResponseDTO>> listarRelacoes() {
        List<ClinicaDoutor> clinicaDoutor = clinicaDoutorRepository.findAll();
        List<ClinicaDoutorResponseDTO> clinicaDoutorResponseDTOS = clinicaDoutor.stream()
                .map(clinicaDoutorMapper::clinicaDoutorToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(clinicaDoutorResponseDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{idClinicaDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDoutorResponseDTO> buscarRelacao(@PathVariable Long idClinicaDoutor) {
        ClinicaDoutor clinicaDoutor = clinicaDoutorRepository.findById(idClinicaDoutor)
                .orElseThrow(() -> new RuntimeException("Relação não encontrada"));
        ClinicaDoutorResponseDTO clinicaDoutorResponseDTO = clinicaDoutorMapper.clinicaDoutorToResponse(clinicaDoutor);
        return new ResponseEntity<>(clinicaDoutorResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idClinicaDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDoutorResponseDTO> deletarRelacao(@PathVariable Long idClinicaDoutor) {
        ClinicaDoutor clinicaDoutor = clinicaDoutorRepository.findById(idClinicaDoutor)
                .orElseThrow(() -> new RuntimeException("Relação não encontrada"));
        clinicaDoutorRepository.delete(clinicaDoutor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
