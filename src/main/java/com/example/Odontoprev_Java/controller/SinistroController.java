package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.procedimento.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimento.ProdecimentoResponseDTO;
import com.example.Odontoprev_Java.DTO.sinistro.SinistroRequestDTO;
import com.example.Odontoprev_Java.DTO.sinistro.SinistroResponseDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Sinistro;
import com.example.Odontoprev_Java.Repository.SinistroRepository;
import com.example.Odontoprev_Java.service.SinistroMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sinistro", produces = {"aplication/json"})
public class SinistroController {

    @Autowired
    private SinistroRepository sinistroRepository;

    @Autowired
    private SinistroMapper sinistroMapper;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SinistroResponseDTO>> listarSinistros() {
        List<Sinistro> sinistros = sinistroRepository.findAll();
        List<SinistroResponseDTO> procedimentosResponse = sinistros.stream()
                .map(sinistroMapper::sinistroToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(procedimentosResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{idSinistro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinistroResponseDTO> buscarSinistro(@PathVariable Long idSinistro) {
        Sinistro sinistro = sinistroRepository.findById(idSinistro)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));
        SinistroResponseDTO sinistroResponseDto = sinistroMapper.sinistroToResponse(sinistro);
        return new ResponseEntity<>(sinistroResponseDto, HttpStatus.OK);
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinistroResponseDTO> criarSinistro() {
        Sinistro sinistroCriado = sinistroRepository.save(new Sinistro());
        SinistroResponseDTO sinistroResponseDto = sinistroMapper.sinistroToResponse(sinistroCriado);
        return new ResponseEntity<>(sinistroResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{idSinistro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinistroResponseDTO> atualizarSinistro(@PathVariable Long idSinistro, @Valid @RequestBody SinistroRequestDTO sinistroRequestDTO)
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
    public ResponseEntity<SinistroResponseDTO> deletarSinistro(@PathVariable Long idSinistro)
    {
        Sinistro sinistro = sinistroRepository.findById(idSinistro)
                .orElseThrow(() -> new RuntimeException("Sinistro não encontrado"));

        sinistroRepository.delete(sinistro);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
