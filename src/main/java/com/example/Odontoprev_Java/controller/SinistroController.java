package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoResponseDTO;
import com.example.Odontoprev_Java.DTO.sinistro.SinistroRequestDTO;
import com.example.Odontoprev_Java.DTO.sinistro.SinistroResponseDTO;
import com.example.Odontoprev_Java.Model.Atendimento;
import com.example.Odontoprev_Java.Model.Sinistro;
import com.example.Odontoprev_Java.Repository.AtendimentoRepository;
import com.example.Odontoprev_Java.Repository.SinistroRepository;
import com.example.Odontoprev_Java.service.SinistroMapper;
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
@RequestMapping(value = "/sinistro", produces = {"aplication/json"})
public class SinistroController {

    @Autowired
    private SinistroRepository sinistroRepository;

    @Autowired
    private SinistroMapper sinistroMapper;
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<SinistroResponseDTO>>> readSinistros() {
        List<Sinistro> sinistros = sinistroRepository.findAll();
        List<EntityModel<SinistroResponseDTO>> procedimentosResponse = sinistros.stream()
                .map(sinistro -> {
                    SinistroResponseDTO sinistroResponse = sinistroMapper.sinistroToResponse(sinistro);

                    return EntityModel.of(sinistroResponse,
                            linkTo(methodOn(SinistroController.class).readSinistros()).withSelfRel(),
                            linkTo(methodOn(SinistroController.class).readSinistroById(null)).withSelfRel(),
                            linkTo(methodOn(SinistroController.class).createSinistro(null)).withRel("post"),
                            linkTo(methodOn(SinistroController.class).updateSinistro(sinistro.getId(), null)).withRel("put"),
                            linkTo(methodOn(SinistroController.class).deleteSinistro(sinistro.getId())).withRel("delete"));
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(procedimentosResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{idSinistro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinistroResponseDTO> readSinistroById(@PathVariable Long idSinistro) {
        Sinistro sinistro = sinistroRepository.findById(idSinistro)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));
        SinistroResponseDTO sinistroResponseDto = sinistroMapper.sinistroToResponse(sinistro);
        return new ResponseEntity<>(sinistroResponseDto, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinistroResponseDTO> createSinistro(@Valid @RequestBody SinistroRequestDTO sinistroRequestDTO)
    {
        Atendimento atendimento = atendimentoRepository.findById(sinistroRequestDTO.atendimento_id().getId())
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));

        Sinistro sinistro = sinistroMapper.sinistroRequest(sinistroRequestDTO);
        Sinistro sinistroSalvo = sinistroRepository.save(sinistro);
        sinistro.setAtendimento(atendimento);
        SinistroResponseDTO sinistroResponseDto = sinistroMapper.sinistroToResponse(sinistroSalvo);
        return new ResponseEntity<>(sinistroResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{idSinistro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinistroResponseDTO> updateSinistro(@PathVariable Long idSinistro, @Valid @RequestBody SinistroRequestDTO sinistroRequestDTO)
    {
        Sinistro sinistro = sinistroRepository.findById(idSinistro)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));

        Sinistro sinistroSalvo = sinistroMapper.sinistroRequest(sinistroRequestDTO);
        sinistroSalvo.setId(idSinistro);
        Sinistro sinistroAtualizado = sinistroRepository.save(sinistroSalvo);
        SinistroResponseDTO sinistroResponseDto = sinistroMapper.sinistroToResponse(sinistroAtualizado);
        return new ResponseEntity<>(sinistroResponseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idSinistro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinistroResponseDTO> deleteSinistro(@PathVariable Long idSinistro)
    {
        Sinistro sinistro = sinistroRepository.findById(idSinistro)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));

        sinistroRepository.delete(sinistro);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
