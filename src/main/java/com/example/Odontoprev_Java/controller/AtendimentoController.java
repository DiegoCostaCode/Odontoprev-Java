package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Atendimento;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import com.example.Odontoprev_Java.Repository.AtendimentoRepository;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.Repository.PacienteRepository;
import com.example.Odontoprev_Java.Repository.ProcedimentorRepository;
import com.example.Odontoprev_Java.service.AtendimentoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/atendimento", produces = {"aplication/json"})
@Tag(name = "api-atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProcedimentorRepository procedimentoRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoResponseDTO> readAtendimento(@PathVariable Long id) {
        Optional<Atendimento> atendimentoSalvo = atendimentoRepository.findById(id);
        if (atendimentoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.atendimentoToResponse(atendimentoSalvo.get());

        return new ResponseEntity<>(atendimentoResponseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/marcar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoResponseDTO> createAtendimento(@RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {

        Paciente paciente = pacienteRepository.findById(atendimentoRequestDTO.paciente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente inválido"));


        Clinica clinica = clinicaRepository.findById(atendimentoRequestDTO.clinica().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica inválido"));


        Atendimento atendimento = atendimentoMapper.atendimentoRequest(atendimentoRequestDTO);

        atendimento.setClinica(clinica);
        atendimento.setPaciente(paciente);
        atendimento.setDescricao(atendimentoRequestDTO.descricao());
        atendimento.setCusto(atendimentoRequestDTO.custo());

        Atendimento atendimentoCriado = atendimentoRepository.save(atendimento);
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.atendimentoToResponse(atendimentoCriado);
        return new ResponseEntity<>(atendimentoResponseDTO, HttpStatus.CREATED);
    }

}
