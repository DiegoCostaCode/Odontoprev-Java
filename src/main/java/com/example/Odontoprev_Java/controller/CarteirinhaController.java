package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.carteirinha.CarteirinhaResponseDTO;
import com.example.Odontoprev_Java.DTO.carteirinha.CarterinhaRequestDTO;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Repository.CarteirinhaRepository;
import com.example.Odontoprev_Java.Repository.PacienteRepository;
import com.example.Odontoprev_Java.Repository.PlanoRepository;
import com.example.Odontoprev_Java.service.CarteirinhaMapper;
import com.example.Odontoprev_Java.service.PacienteMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/carteirinha", produces = {"aplication/json"})
@Tag(name = "api-carteirinha")
public class CarteirinhaController {


    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired(required = true)
    private CarteirinhaMapper carteirinhaMapper;

    @Autowired(required = true)
    private PacienteMapper pacienteMapper;
    @Autowired
    private PlanoRepository planoRepository;
    @Autowired
    private CarteirinhaRepository carteirinhaRepository;


    @PostMapping(value = "/gerar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarteirinhaResponseDTO> gerarCarteirinha(
            @Valid @RequestBody CarterinhaRequestDTO carterinhaRequestDTO){

        Paciente paciente = pacienteRepository.findById(carterinhaRequestDTO.paciente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        Plano plano = planoRepository.findById(carterinhaRequestDTO.plano().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));

        Carteirinha carteirinha = new Carteirinha();
        carteirinha.setPaciente(paciente);
        carteirinha.setPlano(plano);

        Carteirinha carteirinhaCriada = carteirinhaRepository.save(carteirinha);
        CarteirinhaResponseDTO carteirinhaResponse = carteirinhaMapper.carteirinhaToResponse(carteirinhaCriada);

        return new ResponseEntity<>(carteirinhaResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{idCarteirinha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarteirinhaResponseDTO> buscarCarteirinha(@PathVariable Long idCarteirinha){
        Optional<Carteirinha> carteirinha = carteirinhaRepository.findById(idCarteirinha);
        if(carteirinha.isPresent()){
            CarteirinhaResponseDTO carteirinhaResponse = carteirinhaMapper.carteirinhaToResponse(carteirinha.get());
            return ResponseEntity.ok(carteirinhaResponse);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carteirinha não encontrada");
    }

    @PutMapping(value = "/{idPaciente}/{idCarteirinha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarteirinhaResponseDTO> atualizarCarteirinha(@PathVariable Long idCarteirinha,@PathVariable Long idPaciente, @Valid @RequestBody CarterinhaRequestDTO carterinhaRequestDTO){

        Carteirinha carteirinha = carteirinhaRepository.findById(idCarteirinha)
                .orElseThrow(() -> new RuntimeException("Carteirinha não encontrada"));

        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrada"));

        carteirinha.setPaciente(carterinhaRequestDTO.paciente());
        carteirinha.setPlano(carterinhaRequestDTO.plano());

        Carteirinha carteirinhaAtualizada = carteirinhaRepository.save(carteirinha);
        CarteirinhaResponseDTO carteirinhaResponseDTO = carteirinhaMapper.carteirinhaToResponse(carteirinhaAtualizada);
        return ResponseEntity.ok(carteirinhaResponseDTO);
    }

    @DeleteMapping(value = "/{idCarteirinha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletarCarteirinha(@PathVariable Long idCarteirinha){
        Carteirinha carteirinha = carteirinhaRepository.findById(idCarteirinha)
                .orElseThrow(() -> new RuntimeException("Carteirinha não encontrada"));
        carteirinhaRepository.delete(carteirinha);
        return ResponseEntity.noContent().build();
    }


}
