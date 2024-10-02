package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.CarteirinhaResponseDTO;
import com.example.Odontoprev_Java.DTO.CarterinhaRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDTO;
import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Usuario;
import com.example.Odontoprev_Java.Repository.CarteirinhaRepository;
import com.example.Odontoprev_Java.Repository.PlanoRepository;
import com.example.Odontoprev_Java.Repository.UsuarioRepository;
import com.example.Odontoprev_Java.service.CarteirinhaMapper;
import com.example.Odontoprev_Java.service.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/carteirinha", produces = {"aplication/json"})
@Tag(name = "api-carteirinha")
public class CarteirinhaController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = true)
    private CarteirinhaMapper carteirinhaMapper;

    @Autowired(required = true)
    private UsuarioMapper usuarioMapper;


    @GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> readUsuario(@PathVariable Long usuarioId) {
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(usuarioId);
        if (usuarioSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UsuarioResponseDTO usuarioResponse = usuarioMapper.usuarioToResponseDto(usuarioSalvo.get());

        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

}
