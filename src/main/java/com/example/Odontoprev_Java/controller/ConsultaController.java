package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.ConsultaResponseDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDTO;
import com.example.Odontoprev_Java.Model.Consulta;
import com.example.Odontoprev_Java.Model.Usuario;
import com.example.Odontoprev_Java.Repository.ConsultaRepository;
import com.example.Odontoprev_Java.service.ConsultaMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario", produces = {"aplication/json"})
@Tag(name = "api-usuarios")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired(required = true)
    private ConsultaMapper consultaMapper;


    @GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultaResponseDTO> readUsuario(@PathVariable Long usuarioId) {
        Optional<Consulta> consultaSalva = consultaRepository.findById(usuarioId);
        if (consultaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ConsultaResponseDTO consultaResponseDTO = consultaMapper.consultaToResponseDto(consultaSalva.get());

        return new ResponseEntity<>(consultaResponseDTO, HttpStatus.OK);
    }
}
