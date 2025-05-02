package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.auditorDTO.AuditorRequestDTO;
import com.example.Odontoprev_Java.DTO.auditorDTO.AuditorResponseDTO;
import com.example.Odontoprev_Java.DTO.planosDTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.planosDTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.DTO.procedimentoDTO.ProcedimentoResponseDTO;
import com.example.Odontoprev_Java.Model.Auditor;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.procedimento.Procedimento;
import com.example.Odontoprev_Java.service.AuditorService;
import com.example.Odontoprev_Java.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/plano")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @PostMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanoResponseDTO> saveFromAPI(@Valid @RequestBody PlanoRequestDTO planoRequestDTO)
    {

        Plano plano = planoService.savePlano(planoRequestDTO);

        PlanoResponseDTO planoResponseDTO = planoService.planoResponse(plano);

        return new ResponseEntity<>(planoResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanoResponseDTO> updateFromAPI(@PathVariable Long id, @Valid @RequestBody PlanoRequestDTO planoRequestDTO)
    {
        Plano plano = planoService.updatePlano(id,planoRequestDTO);

        PlanoResponseDTO planoResponseDTO = planoService.planoResponse(plano);

        return new ResponseEntity<>(planoResponseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/api/ativar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanoResponseDTO> updateFromAPI(@PathVariable Long id)
    {
        Plano plano = planoService.ativarPlano(id);

        PlanoResponseDTO planoResponseDTO = planoService.planoResponse(plano);

        return new ResponseEntity<>(planoResponseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/")
    public ResponseEntity<List<PlanoResponseDTO>> findAllAtivos() {
        List<Plano> planos = planoService.findPlanosAtivos();

        List<PlanoResponseDTO> planosResponse = planos.stream()
                .map(plano -> planoService.planoResponse(plano))
                .toList();

        return new ResponseEntity<>(planosResponse, HttpStatus.OK);
    }
}
