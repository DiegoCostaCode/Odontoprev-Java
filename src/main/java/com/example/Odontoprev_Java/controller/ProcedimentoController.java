package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.procedimentoDTO.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimentoDTO.ProcedimentoResponseDTO;
import com.example.Odontoprev_Java.model.procedimento.Procedimento;
import com.example.Odontoprev_Java.service.ProcedimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/procedimento")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoService procedimentoService;

    @GetMapping(value = "/api/")
    public ResponseEntity<List<ProcedimentoResponseDTO>> findAllAtivos() {
        List<Procedimento> procedimentos = procedimentoService.findProcedimentosAtivos();

        List<ProcedimentoResponseDTO> procedimentosResponse = procedimentos.stream()
                .map(procedimento -> procedimentoService.procedimentoResponse(procedimento))
                .toList();

        return new ResponseEntity<>(procedimentosResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/api/")
    public ResponseEntity<ProcedimentoResponseDTO> saveFromAPI(@Valid @RequestBody ProcedimentoRequestDTO procedimentoRequest) {
        Procedimento procedimento = procedimentoService.save(procedimentoRequest);

        ProcedimentoResponseDTO procedimentoResponse = procedimentoService.procedimentoResponse(procedimento);

        return new ResponseEntity<>(procedimentoResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/ativar/{id}")
    public ResponseEntity<ProcedimentoResponseDTO> updatStatus(@PathVariable Long id, @Valid @RequestBody ProcedimentoRequestDTO procedimentoRequest) {
        Procedimento procedimento = procedimentoService.update(id, procedimentoRequest);

        ProcedimentoResponseDTO procedimentoResponse = procedimentoService.procedimentoResponse(procedimento);

        return new ResponseEntity<>(procedimentoResponse, HttpStatus.OK);
    }
}
