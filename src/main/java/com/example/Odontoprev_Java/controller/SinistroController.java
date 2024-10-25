package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.procedimento.ProdecimentoResponseDTO;
import com.example.Odontoprev_Java.DTO.sinistro.SinistroResponseDTO;
import com.example.Odontoprev_Java.Model.Sinistro;
import com.example.Odontoprev_Java.Repository.SinistroRepository;
import com.example.Odontoprev_Java.service.SinistroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sinistro", produces = {"aplication/json"})
public class SinistroController {

    @Autowired
    private SinistroRepository sinistroRepository;

    @Autowired
    private SinistroMapper sinistroMapper;

    @GetMapping(value = "/listar-sinistros", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SinistroResponseDTO>> listarSinistros() {
        List<Sinistro> sinistros = sinistroRepository.findAll();
        List<SinistroResponseDTO> procedimentosResponse = sinistros.stream()
                .map(sinistroMapper::sinistroToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(procedimentosResponse, HttpStatus.OK);
    }
}
