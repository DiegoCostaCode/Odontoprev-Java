package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.auditorDTO.AuditorRequestDTO;
import com.example.Odontoprev_Java.DTO.auditorDTO.AuditorResponseDTO;
import com.example.Odontoprev_Java.Model.Auditor;
import com.example.Odontoprev_Java.service.AuditorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auditor")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    @PostMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuditorResponseDTO> saveFromAPI(@Valid @RequestBody AuditorRequestDTO auditorRequestDTO)
    {
        Auditor auditor = auditorService.saveAuditor(auditorRequestDTO);

        AuditorResponseDTO auditorResponseDTO = auditorService.auditorResponse(auditor);

        return new ResponseEntity<>(auditorResponseDTO, HttpStatus.CREATED);
    }
}
