package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorResponseDTO;
import com.example.Odontoprev_Java.DTO.doutor.DoutorResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.ClinicaDoutor;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Repository.ClinicaDoutorRepository;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.Repository.DoutorRepository;
import com.example.Odontoprev_Java.service.ClinicaDoutorMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<List<EntityModel<ClinicaDoutorResponseDTO>>> readClinicaDoutor() {
        List<ClinicaDoutor> clinicaDoutorSalvos = clinicaDoutorRepository.findAll();
        List<EntityModel<ClinicaDoutorResponseDTO>> clinicaDoutorResponseDTOS = clinicaDoutorSalvos.stream()
                .map(clinicaDoutor -> {
                    ClinicaDoutorResponseDTO clinicaDoutorResponse = clinicaDoutorMapper.clinicaDoutorToResponse(clinicaDoutor);
                    return EntityModel.of(clinicaDoutorResponse,
                            linkTo(methodOn(ClinicaDoutorController.class).readClinicaDoutor()).withSelfRel(),
                            linkTo(methodOn(ClinicaDoutorController.class).readClinicaDoutor()).withRel("post"),
                            linkTo(methodOn(ClinicaDoutorController.class).readClinicaDoutorById(clinicaDoutor.getId())).withRel("get"),
                            linkTo(methodOn(ClinicaDoutorController.class).updateClinicaDoutor(clinicaDoutor.getId(), null)).withRel("put"),
                            linkTo(methodOn(ClinicaDoutorController.class).deleteClinicaDoutor(clinicaDoutor.getId())).withRel("delete"));
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(clinicaDoutorResponseDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/{idClinicaDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDoutorResponseDTO> updateClinicaDoutor(@PathVariable Long idClinicaDoutor, @Valid @RequestBody ClinicaDoutorRequestDTO clinicaDoutorRequestDTO) {
        ClinicaDoutor clinicaDoutor = clinicaDoutorRepository.findById(idClinicaDoutor)
                .orElseThrow(() -> new RuntimeException("Relação não encontrada"));

        Clinica clinica = clinicaRepository.findById(clinicaDoutorRequestDTO.clinicaId().getId())
                .orElseThrow(() -> new RuntimeException("Clinica não encontrada"));

        Doutor doutor = doutorRepository.findById(clinicaDoutorRequestDTO.doutorId().getId())
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        clinicaDoutor.setClinica(clinica);
        clinicaDoutor.setDoutor(doutor);
        clinicaDoutor.setDataRelacionamento(clinicaDoutorRequestDTO.dataRelacionamento());
        clinicaDoutor.setDataFimRelacionamento(clinicaDoutorRequestDTO.dataFimRelacionamento());

        clinicaDoutorRepository.save(clinicaDoutor);

        ClinicaDoutorResponseDTO clinicaDoutorResponseDTO = clinicaDoutorMapper.clinicaDoutorToResponse(clinicaDoutor);
        return new ResponseEntity<>(clinicaDoutorResponseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{idClinicaDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDoutorResponseDTO> readClinicaDoutorById(@PathVariable Long idClinicaDoutor) {
        ClinicaDoutor clinicaDoutor = clinicaDoutorRepository.findById(idClinicaDoutor)
                .orElseThrow(() -> new RuntimeException("Relação não encontrada"));
        ClinicaDoutorResponseDTO clinicaDoutorResponseDTO = clinicaDoutorMapper.clinicaDoutorToResponse(clinicaDoutor);
        return new ResponseEntity<>(clinicaDoutorResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idClinicaDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDoutorResponseDTO> deleteClinicaDoutor(@PathVariable Long idClinicaDoutor) {
        ClinicaDoutor clinicaDoutor = clinicaDoutorRepository.findById(idClinicaDoutor)
                .orElseThrow(() -> new RuntimeException("Relação não encontrada"));
        clinicaDoutorRepository.delete(clinicaDoutor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
