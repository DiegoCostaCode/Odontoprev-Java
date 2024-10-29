package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimento.ProdecimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import com.example.Odontoprev_Java.Repository.ProcedimentoRepository;
import com.example.Odontoprev_Java.service.ProdecimentoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/procedimento", produces = {"aplication/json"})
public class ProdecimentoController {

    @Autowired
    private ProcedimentoRepository prodecimentoRepository;

    @Autowired
    private ProdecimentoMapper prodecimentoMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdecimentoResponseDTO>> listarProcedimentos() {
        List<Procedimento> procedimentos = prodecimentoRepository.findAll();
        List<ProdecimentoResponseDTO> procedimentosResponse = procedimentos.stream()
                .map(prodecimentoMapper::procedimentoToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(procedimentosResponse, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdecimentoResponseDTO> criarProcedimento(@Valid @RequestBody ProcedimentoRequestDTO prodecimentoRequestDTO) {
        Procedimento prodecimentoConvertido = prodecimentoMapper.procedimentoRequest(prodecimentoRequestDTO);
        Procedimento prodecimentoCriado = prodecimentoRepository.save(prodecimentoConvertido);
        ProdecimentoResponseDTO prodecimentoResponseDto = prodecimentoMapper.procedimentoToResponse(prodecimentoCriado);
        return new ResponseEntity<>(prodecimentoResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{idProcedimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdecimentoResponseDTO> atualizarProcedimento(@PathVariable Long idProcedimento, @Valid @RequestBody ProcedimentoRequestDTO prodecimentoRequestDTO) {
        Procedimento prodecimento = prodecimentoRepository.findById(idProcedimento)
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        prodecimento.setDescricao(prodecimentoRequestDTO.descricao());

        Procedimento prodecimentoAtualizado = prodecimentoRepository.save(prodecimento);
        ProdecimentoResponseDTO prodecimentoResponseDto = prodecimentoMapper.procedimentoToResponse(prodecimentoAtualizado);
        return new ResponseEntity<>(prodecimentoResponseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idProcedimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletarProcedimento(@PathVariable Long idProcedimento) {
        Procedimento prodecimento = prodecimentoRepository.findById(idProcedimento)
                .orElseThrow(() -> new RuntimeException("Procedimento não encontrado"));

        prodecimentoRepository.delete(prodecimento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}