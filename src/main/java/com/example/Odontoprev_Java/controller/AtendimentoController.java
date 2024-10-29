package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoResponseDTO;
import com.example.Odontoprev_Java.Model.*;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import com.example.Odontoprev_Java.Repository.*;
import com.example.Odontoprev_Java.service.AtendimentoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private SinistroRepository sinistroRepository;


    @GetMapping(value = "/{idAtendimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoResponseDTO> readAtendimento(@PathVariable Long idAtendimento) {
        Optional<Atendimento> atendimentoSalvo = atendimentoRepository.findById(idAtendimento);
        if (atendimentoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.atendimentoToResponse(atendimentoSalvo.get());

        return new ResponseEntity<>(atendimentoResponseDTO, HttpStatus.OK);
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoResponseDTO> createAtendimento(@RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {

        Paciente paciente = pacienteRepository.findById(atendimentoRequestDTO.paciente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente inválido!"));

        Procedimento procedimento = procedimentoRepository.findById(atendimentoRequestDTO.procedimento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedimento inválido!"));

        Clinica clinica = clinicaRepository.findById(atendimentoRequestDTO.clinica().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica inválida!"));

        Atendimento atendimento = atendimentoMapper.atendimentoRequest(atendimentoRequestDTO);

        atendimento.setClinica(clinica);
        atendimento.setProcedimento(procedimento);
        atendimento.setPaciente(paciente);
        atendimento.setDescricao(atendimentoRequestDTO.descricao());
        atendimento.setDataHoraAtendimento(atendimentoRequestDTO.diaHoraAtendimento());
        atendimento.setCusto(atendimentoRequestDTO.custo());

        Atendimento atendimentoCriado = atendimentoRepository.save(atendimento);
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.atendimentoToResponse(atendimentoCriado);

        Sinistro sinistro = new Sinistro();

        if(atendimento.getCusto() > procedimento.getDescricao().getOrcamentoMedio())
        {
            sinistro.setDescricao("Sinistro gerado por atendimento com custo acima do orçamento médio");
            sinistro.setAtendimento(atendimento);
            sinistro.setCusto_excedente(atendimento.getCusto() - procedimento.getDescricao().getOrcamentoMedio());

            Sinistro sinistroCriado = sinistroRepository.save(sinistro);
        }

        return new ResponseEntity<>(atendimentoResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{idAtendimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoResponseDTO> updateAtendimento(@PathVariable Long idAtendimento, @RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {
        Optional<Atendimento> atendimentoSalvo = atendimentoRepository.findById(idAtendimento);
        if (atendimentoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Paciente paciente = pacienteRepository.findById(atendimentoRequestDTO.paciente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente inválido!"));

        Procedimento procedimento = procedimentoRepository.findById(atendimentoRequestDTO.procedimento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedimento inválido!"));

        Clinica clinica = clinicaRepository.findById(atendimentoRequestDTO.clinica().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica inválida!"));

        Atendimento atendimento = atendimentoSalvo.get();

        atendimento.setClinica(clinica);
        atendimento.setProcedimento(procedimento);
        atendimento.setPaciente(paciente);
        atendimento.setDescricao(atendimentoRequestDTO.descricao());
        atendimento.setCusto(atendimentoRequestDTO.custo());

        Atendimento atendimentoAtualizado = atendimentoRepository.save(atendimento);
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.atendimentoToResponse(atendimentoAtualizado);

        return new ResponseEntity<>(atendimentoResponseDTO, HttpStatus.OK);
    }
}
