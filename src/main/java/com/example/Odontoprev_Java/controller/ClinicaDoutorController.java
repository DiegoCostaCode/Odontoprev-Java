package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.clinicaDoutor.ClinicaDoutorResponseDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.ClinicaDoutor;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Repository.ClinicaDoutorRepository;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.Repository.DoutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cadastro-relacionamento")
public class ClinicaDoutorController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private ClinicaDoutorRepository clinicaDoutorRepository;



    @PostMapping(value = "/{clinicaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaDoutor> createClinicaDoutor(@PathVariable Long clinicaId, @Valid @RequestBody ClinicaDoutorRequestDTO clinicaDoutorRequestDTO) {

        Clinica clinica = clinicaRepository.findById(clinicaId)
                .orElseThrow(() -> new RuntimeException("Clinica não encontrado"));

        Doutor doutor = doutorRepository.findById(clinicaDoutorRequestDTO.clinicaId().getId())
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        ClinicaDoutor clinicaDoutor = new ClinicaDoutor();
        clinicaDoutor.setClinica(clinica);
        clinicaDoutor.setDoutor(doutor);
        clinicaDoutor.setDataRelacionamento(clinicaDoutorRequestDTO.dataRelacionamento());
        clinicaDoutor.setDataFimRelacionamento(clinicaDoutorRequestDTO.dataFimRelacionamento());

        clinicaDoutorRepository.save(clinicaDoutor);

        return new ResponseEntity<>(clinicaDoutor, HttpStatus.CREATED);
    }

}
