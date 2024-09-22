package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.UsuarioRequestDto;
import com.example.Odontoprev_Java.DTO.UsuarioResponseDto;
import com.example.Odontoprev_Java.Model.Usuario;
import com.example.Odontoprev_Java.Repository.UsuarioRepository;
import com.example.Odontoprev_Java.service.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//localhost:8080/usuarios
@RequestMapping(value = "/usuario", produces = {"aplication/json"})
@Tag(name = "api-usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired(required = true)
    private UsuarioMapper usuarioMapper;

    Pageable paginacao = PageRequest.of(0, 2, Sort.by("nome").descending());

    @Operation(summary = "Cria o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> createUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequest)
    {
        Usuario usuarioConvertida = usuarioMapper.requestRecordToUsuario(usuarioRequest);
        Usuario usuarioCriada = usuarioRepository.save(usuarioConvertida);
        UsuarioResponseDto usuarioResponseDto = usuarioMapper.usuarioToResponseDto(usuarioCriada);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.CREATED);
    }
}

