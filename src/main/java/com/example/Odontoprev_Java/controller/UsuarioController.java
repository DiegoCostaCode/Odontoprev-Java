package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioRequestDto;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDto;
import com.example.Odontoprev_Java.Model.*;
import com.example.Odontoprev_Java.Repository.*;
import com.example.Odontoprev_Java.service.UsuarioMapper;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario", produces = {"aplication/json"})
@Tag(name = "api-usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = true)
    private UsuarioMapper usuarioMapper;


    @Operation(summary = "Cria o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping(value = "/cadastro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDto> createUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequest)
    {
        Usuario usuarioConvertida = usuarioMapper.requestRecordToUsuario(usuarioRequest);
        Usuario usuarioCriada = usuarioRepository.save(usuarioConvertida);
        UsuarioResponseDto usuarioResponseDto = usuarioMapper.usuarioToResponseDto(usuarioCriada);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{usuarioId}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adiciona endereco a um usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereo adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<UsuarioResponseDto> addEnderecoToUsuario(
            @PathVariable Long usuarioId,
            @RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        // Verificar se o usuário existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Mapear EnderecoRequestDTO para a entidade Endereco
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequestDTO.rua());
        endereco.setNumero(enderecoRequestDTO.numero());
        endereco.setCep(enderecoRequestDTO.cep());
        endereco.setBairro(enderecoRequestDTO.bairro());
        endereco.setCidade(enderecoRequestDTO.cidade());
        endereco.setEstado(enderecoRequestDTO.estado());
        endereco.setPais(enderecoRequestDTO.pais());

        // Adicionar o endereço ao usuário
        usuario.setEndereco(endereco);

        // Salvar o usuário com o novo endereço
        usuarioRepository.save(usuario);

        // Mapear o usuário salvo para o DTO de resposta
        UsuarioResponseDto usuarioResponse = usuarioMapper.usuarioToResponseDto(usuario);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDto> readUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioReserva = usuarioRepository.findById(id);
        if (usuarioReserva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UsuarioResponseDto usuarioResponse = usuarioMapper.usuarioToResponseDto(usuarioReserva.get());

        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }
}

