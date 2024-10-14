package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.consulta.ConsultaRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDTO;
import com.example.Odontoprev_Java.Model.*;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import com.example.Odontoprev_Java.Repository.*;
import com.example.Odontoprev_Java.service.ConsultaMapper;
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

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario", produces = {"aplication/json"})
@Tag(name = "api-usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired(required = true)
    private UsuarioMapper usuarioMapper;

    @Autowired()
    private ConsultaMapper consultaMapper;

    @Operation(summary = "Cria o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping(value = "/cadastro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequest)
    {
        Usuario usuarioConvertida = usuarioMapper.requestRecordToUsuario(usuarioRequest);
        Usuario usuarioCriada = usuarioRepository.save(usuarioConvertida);
        UsuarioResponseDTO usuarioResponseDto = usuarioMapper.usuarioToResponseDto(usuarioCriada);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Consulta usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> readUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioReserva = usuarioRepository.findById(id);
        if (usuarioReserva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UsuarioResponseDTO usuarioResponse = usuarioMapper.usuarioToResponseDto(usuarioReserva.get());

        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{usuarioId}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adiciona endereco a um usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereo adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<UsuarioResponseDTO> addEnderecoToUsuario(
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
        UsuarioResponseDTO usuarioResponse = usuarioMapper.usuarioToResponseDto(usuario);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }


    @Operation(summary = "Cria uma carteirinha para um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carteirinha criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou plano não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PutMapping(value = "/{usuarioId}/carteirinha/{planoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> createCarteirinhaForUser   (@PathVariable Long usuarioId, @PathVariable Long planoId) {

        // Verificar se o usuário existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Verificar se o plano existe
        Plano plano = planoRepository.findById(planoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));

        // Criar uma nova carteirinha com os detalhes do plano
        Carteirinha carteirinha = new Carteirinha();
        carteirinha.setEmissao(new Date()); // Data de emissão atual
        carteirinha.setValidade(new Date()); // Data de validade atual
        carteirinha.setUsuario(usuario);
        carteirinha.setPlano(plano);

        // Adicionar a carteirinha ao usuário
        usuario.setCarteirinha(carteirinha);

        // Salvar o usuário com a nova carteirinha
        usuarioRepository.save(usuario);

        // Mapear o usuário salvo para o DTO de resposta
        UsuarioResponseDTO usuarioResponse = usuarioMapper.usuarioToResponseDto(usuario);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

    @Operation(summary = "Marca uma consulta para um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta marcada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário, clínica ou serviço não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping(value = "/{usuarioId}/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> marcarConsulta(@PathVariable Long usuarioId, @RequestBody ConsultaRequestDTO consultaRequestDTO) {

        // Verificar se o usuário existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Verificar se a clínica existe
        Clinica clinica = clinicaRepository.findById(consultaRequestDTO.clinicaId().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada"));

        // Verificar se o serviço é prestado pela clínica
        if (!clinica.getServicos().contains(consultaRequestDTO.servico())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A clínica não presta o serviço solicitado");
        }

        // Criar uma nova consulta com os detalhes do serviço e clínica
        Consulta consulta = new Consulta();
        consulta.setAgendamento(consultaRequestDTO.agendamento());
        consulta.setServico(consultaRequestDTO.servico());
        consulta.setUsuario(usuario);
        consulta.setClinica(clinica);
        consulta.setObservacoes(consultaRequestDTO.observacoes());

        // Salvar a consulta
        Consulta consultaSalva = consultaRepository.save(consulta);

        // Mapear o usuário salvo para o DTO de resposta
        UsuarioResponseDTO usuarioResponse = usuarioMapper.usuarioToResponseDto(usuario);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

}

