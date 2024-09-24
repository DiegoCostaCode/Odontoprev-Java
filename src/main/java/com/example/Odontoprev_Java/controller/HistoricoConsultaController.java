package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.HistoricoConsultaResponseDTO;
import com.example.Odontoprev_Java.Model.HistoricoConsulta;
import com.example.Odontoprev_Java.Repository.HistoricoConsultaRepository;
import com.example.Odontoprev_Java.service.HistoricoConsultaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/historico", produces = {"aplication/json"})
@Tag(name = "api-historico")
public class HistoricoConsultaController {

    @Autowired
    private HistoricoConsultaRepository historicoConsultaRepository;
    @Autowired(required = true)
    private HistoricoConsultaMapper historicoConsultaMapper;

    @Operation(summary = "Retorna um livro dado o seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HistoricoConsultaResponseDTO> readHistoricoConsulta(@PathVariable Long id) {
        Optional<HistoricoConsulta> historicoSalva = historicoConsultaRepository.findById(id);
        if (historicoSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        HistoricoConsultaResponseDTO historicoConsultaResponse = historicoConsultaMapper.historicoConsultaToResponseDTO(historicoSalva.get());

        return new ResponseEntity<>(historicoConsultaResponse, HttpStatus.OK);
    }
}
