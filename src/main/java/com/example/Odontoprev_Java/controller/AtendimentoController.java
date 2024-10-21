package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Atendimento;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Repository.AtendimentoRepository;
import com.example.Odontoprev_Java.Repository.PacienteRepository;
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
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private PacienteRepository pacienteRepository;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoResponseDTO> readAtendimento(@PathVariable Long id) {
        Optional<Atendimento> atendimentoSalvo = atendimentoRepository.findById(id);
        if (atendimentoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.atendimentoToResponse(atendimentoSalvo.get());

        return new ResponseEntity<>(atendimentoResponseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/marcar/{pacienteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoResponseDTO> createAtendimento(@PathVariable long pacienteId,@RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente inválido"));

        Atendimento atendimento = atendimentoMapper.atendimentoRequest(atendimentoRequestDTO);

        atendimento.setClinica(atendimentoRequestDTO.clinica());
        atendimento.setDescricao(atendimentoRequestDTO.descricao());
        atendimento.setData(atendimentoRequestDTO.data());
        atendimento.setCusto(atendimentoRequestDTO.custo());
        atendimento.setProcedimento(atendimentoRequestDTO.procedimento());
        atendimento.setPaciente(paciente);

        Atendimento atendimentoCriado = atendimentoRepository.save(atendimento);
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.atendimentoToResponse(atendimentoCriado);
        return new ResponseEntity<>(atendimentoResponseDTO, HttpStatus.CREATED);
    }

}
