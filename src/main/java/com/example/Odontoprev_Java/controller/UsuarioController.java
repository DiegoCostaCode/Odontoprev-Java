package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.usuario.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.*;
import com.example.Odontoprev_Java.Repository.*;
import com.example.Odontoprev_Java.service.EnderecoMapper;
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

import java.util.Optional;

@RestController
@RequestMapping(value = "/paciente", produces = {"aplication/json"})
@Tag(name = "api-usuarios")
public class UsuarioController {

//    @Autowired
//    private PacienteRepository pacienteRepository;
//
//    @Autowired
//    private PlanoRepository planoRepository;
//
//    @Autowired
//    private AtendimentoRepository atendimentoRepository;
//
//    @Autowired
//    private ClinicaRepository clinicaRepository;
//    @Autowired()
//    private ConsultaMapper consultaMapper;

    @Autowired(required = true)
    private PacienteMapper pacienteMapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;


    @Operation(summary = "Cria o paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente registrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping(value = "/cadastro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> createUsuario(@Valid @RequestBody PacienteRequestDTO pacienteRequest)
    {
        Paciente pacienteConvertida = pacienteMapper.requestToPlano(pacienteRequest);
        Paciente pacienteCriada = pacienteRepository.save(pacienteConvertida);
        PacienteResponseDTO pacienteResponseDto = pacienteMapper.pacienteResponseDTO(pacienteCriada);
        return new ResponseEntity<>(pacienteResponseDto, HttpStatus.CREATED);

    }


    @GetMapping(value = "/{paciente_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> readUsuario(@PathVariable Long paciente_id) {
        Optional<Paciente> pacienteSalvo = pacienteRepository.findById(paciente_id);
        if (pacienteSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        PacienteResponseDTO pacienteResponse = pacienteMapper.pacienteResponseDTO(pacienteSalvo.get());

        return new ResponseEntity<>(pacienteResponse, HttpStatus.OK);
    };

//
//    @Operation(summary = "Cria uma carteirinha para um usuário existente")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Carteirinha criada com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Usuário ou plano não encontrado"),
//            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
//    })
//    @PutMapping(value = "/{usuarioId}/carteirinha/{planoId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PacienteResponseDTO> createCarteirinhaForUser   (@PathVariable Long usuarioId, @PathVariable Long planoId) {
//
//        // Verificar se o usuário existe
//        Paciente paciente = pacienteRepository.findById(usuarioId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
//
//        // Verificar se o plano existe
//        Plano plano = planoRepository.findById(planoId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));
//
//        // Criar uma nova carteirinha com os detalhes do plano
//        Carteirinha carteirinha = new Carteirinha();
//        carteirinha.setEmissao(new Date()); // Data de emissão atual
//        carteirinha.setValidade(new Date()); // Data de validade atual
//        carteirinha.setUsuario(paciente);
//        carteirinha.setPlano(plano);
//
//        // Adicionar a carteirinha ao usuário
//        paciente.setCarteirinha(carteirinha);
//
//        // Salvar o usuário com a nova carteirinha
//        pacienteRepository.save(paciente);
//
//        // Mapear o usuário salvo para o DTO de resposta
//        PacienteResponseDTO usuarioResponse = pacienteMapper.usuarioToResponseDto(paciente);
//
//        // Retornar o usuário atualizado
//        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
//    }
//
//    @Operation(summary = "Marca uma consulta para um usuário existente")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Atendimento marcada com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Usuário, clínica ou serviço não encontrado"),
//            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
//    })
//    @PostMapping(value = "/{usuarioId}/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PacienteResponseDTO> marcarConsulta(@PathVariable Long usuarioId, @RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {
//
//        // Verificar se o usuário existe
//        Paciente paciente = pacienteRepository.findById(usuarioId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
//
//        // Verificar se a clínica existe
//        Clinica clinica = clinicaRepository.findById(atendimentoRequestDTO.clinicaId().getId())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada"));
//
//        // Verificar se o serviço é prestado pela clínica
//        if (!clinica.getServicos().contains(atendimentoRequestDTO.servico())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A clínica não presta o serviço solicitado");
//        }
//
//        // Criar uma nova atendimento com os detalhes do serviço e clínica
//        Atendimento atendimento = new Atendimento();
//        atendimento.setAgendamento(atendimentoRequestDTO.agendamento());
//        atendimento.setServico(atendimentoRequestDTO.servico());
//        atendimento.setUsuario(paciente);
//        atendimento.setClinica(clinica);
//        atendimento.setObservacoes(atendimentoRequestDTO.observacoes());
//
//        // Salvar a atendimento
//        Atendimento atendimentoSalva = atendimentoRepository.save(atendimento);
//
//        // Mapear o usuário salvo para o DTO de resposta
//        PacienteResponseDTO usuarioResponse = pacienteMapper.usuarioToResponseDto(paciente);
//
//        // Retornar o usuário atualizado
//        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
//    }

}

