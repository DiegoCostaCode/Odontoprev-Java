package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.atendimento.AtendimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.*;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import com.example.Odontoprev_Java.Repository.*;
import com.example.Odontoprev_Java.service.ConsultaMapper;
import com.example.Odontoprev_Java.service.PacienteMapper;
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
    private PacienteRepository pacienteRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired(required = true)
    private PacienteMapper pacienteMapper;

    @Autowired()
    private ConsultaMapper consultaMapper;

    @Operation(summary = "Cria o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping(value = "/cadastro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> createUsuario(@Valid @RequestBody PacienteRequestDTO usuarioRequest)
    {
        Paciente pacienteConvertida = pacienteMapper.requestRecordToUsuario(usuarioRequest);
        Paciente pacienteCriada = pacienteRepository.save(pacienteConvertida);
        PacienteResponseDTO pacienteResponseDto = pacienteMapper.usuarioToResponseDto(pacienteCriada);
        return new ResponseEntity<>(pacienteResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Atendimento usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> readUsuario(@PathVariable Long id) {
        Optional<Paciente> usuarioReserva = pacienteRepository.findById(id);
        if (usuarioReserva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        PacienteResponseDTO usuarioResponse = pacienteMapper.usuarioToResponseDto(usuarioReserva.get());

        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{usuarioId}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adiciona endereco a um paciente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereo adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<PacienteResponseDTO> addEnderecoToUsuario(
            @PathVariable Long usuarioId,
            @RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        // Verificar se o usuário existe
        Paciente paciente = pacienteRepository.findById(usuarioId)
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
        paciente.setEndereco(endereco);

        // Salvar o usuário com o novo endereço
        pacienteRepository.save(paciente);

        // Mapear o usuário salvo para o DTO de resposta
        PacienteResponseDTO usuarioResponse = pacienteMapper.usuarioToResponseDto(paciente);

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
    public ResponseEntity<PacienteResponseDTO> createCarteirinhaForUser   (@PathVariable Long usuarioId, @PathVariable Long planoId) {

        // Verificar se o usuário existe
        Paciente paciente = pacienteRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Verificar se o plano existe
        Plano plano = planoRepository.findById(planoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));

        // Criar uma nova carteirinha com os detalhes do plano
        Carteirinha carteirinha = new Carteirinha();
        carteirinha.setEmissao(new Date()); // Data de emissão atual
        carteirinha.setValidade(new Date()); // Data de validade atual
        carteirinha.setUsuario(paciente);
        carteirinha.setPlano(plano);

        // Adicionar a carteirinha ao usuário
        paciente.setCarteirinha(carteirinha);

        // Salvar o usuário com a nova carteirinha
        pacienteRepository.save(paciente);

        // Mapear o usuário salvo para o DTO de resposta
        PacienteResponseDTO usuarioResponse = pacienteMapper.usuarioToResponseDto(paciente);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

    @Operation(summary = "Marca uma consulta para um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atendimento marcada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário, clínica ou serviço não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping(value = "/{usuarioId}/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> marcarConsulta(@PathVariable Long usuarioId, @RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {

        // Verificar se o usuário existe
        Paciente paciente = pacienteRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Verificar se a clínica existe
        Clinica clinica = clinicaRepository.findById(atendimentoRequestDTO.clinicaId().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada"));

        // Verificar se o serviço é prestado pela clínica
        if (!clinica.getServicos().contains(atendimentoRequestDTO.servico())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A clínica não presta o serviço solicitado");
        }

        // Criar uma nova atendimento com os detalhes do serviço e clínica
        Atendimento atendimento = new Atendimento();
        atendimento.setAgendamento(atendimentoRequestDTO.agendamento());
        atendimento.setServico(atendimentoRequestDTO.servico());
        atendimento.setUsuario(paciente);
        atendimento.setClinica(clinica);
        atendimento.setObservacoes(atendimentoRequestDTO.observacoes());

        // Salvar a atendimento
        Atendimento atendimentoSalva = atendimentoRepository.save(atendimento);

        // Mapear o usuário salvo para o DTO de resposta
        PacienteResponseDTO usuarioResponse = pacienteMapper.usuarioToResponseDto(paciente);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

}

