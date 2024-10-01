package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.endereco.BairroResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioRequestDto;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDto;
import com.example.Odontoprev_Java.Model.*;
import com.example.Odontoprev_Java.Repository.*;
import com.example.Odontoprev_Java.service.BairroMapper;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario", produces = {"aplication/json"})
@Tag(name = "api-usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = true)
    private UsuarioMapper usuarioMapper;


    Pageable paginacao = PageRequest.of(0, 2, Sort.by("nome").descending());
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

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
    @Operation(summary = "Adiciona um endereço ao usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<UsuarioResponseDto> addEnderecoToUsuario(
            @PathVariable Long usuarioId,
            @RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        // Buscar o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Mapear o Endereço
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequestDTO.rua());
        endereco.setNumero(enderecoRequestDTO.numero());
        endereco.setCep(enderecoRequestDTO.cep());

        // Mapear o Bairro
        Bairro bairro = new Bairro();
        bairro.setBairro(enderecoRequestDTO.bairro().getBairro());

        // Mapear a Cidade
        Cidade cidade = new Cidade();
        cidade.setCidade(enderecoRequestDTO.bairro().getCidade().getCidade());

        // Mapear o Estado
        Estado estado = new Estado();
        estado.setEstado(enderecoRequestDTO.bairro().getCidade().getEstado().getEstado());

        // Mapear o País
        Pais pais = new Pais();
        pais.setPais(enderecoRequestDTO.bairro().getCidade().getEstado().getPais().getPais());

        // Relacionar as entidades
        estado.setPais(pais); // Relacionar Estado com País
        cidade.setEstado(estado); // Relacionar Cidade com Estado
        bairro.setCidade(cidade); // Relacionar Bairro com Cidade
        endereco.setBairro(bairro); // Relacionar Endereço com Bairro

        // Relacionar o endereço ao usuário
        usuario.setEndereco(endereco);

        // Salvar o usuário (cascade = CascadeType.ALL fará o resto)
        usuarioRepository.save(usuario);


        // Mapear a resposta para o DTO
        UsuarioResponseDto usuarioResponseDto = usuarioMapper.usuarioToResponseDto(usuario);

        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.OK);
    }


}

