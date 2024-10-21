package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.plano.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.plano.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Repository.PlanoRepository;
import com.example.Odontoprev_Java.service.PlanoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping(value = "/plano", produces = {"aplication/json"})
@Tag(name = "api-planos")
public class PlanoController {

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private PlanoMapper planoMapper;

    @GetMapping(value = "/listar-planos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanoResponseDTO>> listarPlanos(){
        List<Plano> planos = planoRepository.findAll();
        List<PlanoResponseDTO> planosResponse = planos.stream()
                .map(planoMapper::planoResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(planosResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/ativar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanoResponseDTO> planoSalvoo(@Valid @RequestBody PlanoRequestDTO planoRequestDTO)
    {
        Plano planoConvertido = planoMapper.requestToPlano(planoRequestDTO);
        Plano planoCriado = planoRepository.save(planoConvertido);
        PlanoResponseDTO planoResponseDto = planoMapper.planoResponseDTO(planoCriado);
        return new ResponseEntity<>(planoResponseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/atualizar/{plano_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanoResponseDTO> atualizarPlano(@PathVariable Long plano_id, @Valid @RequestBody PlanoRequestDTO planoRequestDTO)
    {
        Plano plano = planoRepository.findById(plano_id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        plano.setNome(planoRequestDTO.nome());
        plano.setDescricao(planoRequestDTO.descricao());
        plano.setPreco(planoRequestDTO.preco());
        plano.setAtivo(planoRequestDTO.ativo());

        Plano planoAtualizado = planoRepository.save(plano);
        PlanoResponseDTO planoResponseDTO = planoMapper.planoResponseDTO(planoAtualizado);
        return new ResponseEntity<>(planoResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{plano_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanoResponseDTO> deletarPlano(@PathVariable Long plano_id)
    {
        Plano plano = planoRepository.findById(plano_id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        planoRepository.delete(plano);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
