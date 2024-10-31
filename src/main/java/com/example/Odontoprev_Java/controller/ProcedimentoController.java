package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import com.example.Odontoprev_Java.Repository.ProcedimentoRepository;
import com.example.Odontoprev_Java.service.ProcedimentoMapper;
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
@RequestMapping(value = "/procedimento", produces = {"aplication/json"})
public class ProcedimentoController {

    @Autowired
    private ProcedimentoRepository prodecimentoRepository;

    @Autowired
    private ProcedimentoMapper procedimentoMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<ProcedimentoResponseDTO>>> readProcedimento() {
        List<Procedimento> procedimentos = prodecimentoRepository.findAll();
        List<EntityModel<ProcedimentoResponseDTO>> procedimentosResponse = procedimentos.stream()
                .map(procedimento -> {
                    ProcedimentoResponseDTO procedimentoResponse = procedimentoMapper.procedimentoToResponse(procedimento);

                    return EntityModel.of(procedimentoResponse,
                            linkTo(methodOn(ProcedimentoController.class).readProcedimento()).withSelfRel(),
                            linkTo(methodOn(ProcedimentoController.class).createProcedimento(null)).withRel("post"),
                            linkTo(methodOn(ProcedimentoController.class).updateProcedimento(procedimento.getId(), null)).withRel("put"),
                            linkTo(methodOn(ProcedimentoController.class).deleteProcedimento(procedimento.getId())).withRel("delete"));
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(procedimentosResponse, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedimentoResponseDTO> createProcedimento(@Valid @RequestBody ProcedimentoRequestDTO prodecimentoRequestDTO) {
        Procedimento prodecimentoConvertido = procedimentoMapper.procedimentoRequest(prodecimentoRequestDTO);
        Procedimento prodecimentoCriado = prodecimentoRepository.save(prodecimentoConvertido);
        ProcedimentoResponseDTO procedimentoResponseDto = procedimentoMapper.procedimentoToResponse(prodecimentoCriado);
        return new ResponseEntity<>(procedimentoResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{idProcedimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcedimentoResponseDTO> updateProcedimento(@PathVariable Long idProcedimento, @Valid @RequestBody ProcedimentoRequestDTO prodecimentoRequestDTO) {
        Procedimento prodecimento = prodecimentoRepository.findById(idProcedimento)
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        prodecimento.setDescricao(prodecimentoRequestDTO.descricao());

        Procedimento prodecimentoAtualizado = prodecimentoRepository.save(prodecimento);
        ProcedimentoResponseDTO procedimentoResponseDto = procedimentoMapper.procedimentoToResponse(prodecimentoAtualizado);
        return new ResponseEntity<>(procedimentoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idProcedimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProcedimento(@PathVariable Long idProcedimento) {
        Procedimento prodecimento = prodecimentoRepository.findById(idProcedimento)
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        prodecimentoRepository.delete(prodecimento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}