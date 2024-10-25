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

    @GetMapping(value = "/listar-procedimentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdecimentoResponseDTO>> listarProcedimentos(){
        List<Procedimento> procedimentos = prodecimentoRepository.findAll();
        List<ProdecimentoResponseDTO> procedimentosResponse = procedimentos.stream()
                .map(prodecimentoMapper::procedimentoToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(procedimentosResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdecimentoResponseDTO> prodecimentoSalvo(@Valid @RequestBody ProcedimentoRequestDTO prodecimentoRequestDTO)
    {
        Procedimento prodecimentoConvertido = prodecimentoMapper.procedimentoRequest(prodecimentoRequestDTO);
        Procedimento prodecimentoCriado = prodecimentoRepository.save(prodecimentoConvertido);
        ProdecimentoResponseDTO prodecimentoResponseDto = prodecimentoMapper.procedimentoToResponse(prodecimentoCriado);
        return new ResponseEntity<>(prodecimentoResponseDto, HttpStatus.CREATED);
    }


}
