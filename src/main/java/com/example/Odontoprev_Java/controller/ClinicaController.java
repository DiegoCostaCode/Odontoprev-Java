package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDto;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Endereco;
import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.Usuario;
import com.example.Odontoprev_Java.Repository.ClinicaRepository;
import com.example.Odontoprev_Java.service.ClinicaMapper;
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

import java.util.Optional;

@RestController
@RequestMapping(value = "/clinica", produces = {"aplication/json"})
@Tag(name = "api-clinicas")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;
    @Autowired(required = true)
    private ClinicaMapper clinicaMapper;

    Pageable paginacao = PageRequest.of(0, 2, Sort.by("servico").descending());

    @Operation(summary = "Registra clínicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clínica registrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content =  @Content(schema = @Schema()))
    })
    @PostMapping(value = "/cadastro",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> createClinica(@Valid @RequestBody ClinicaRequestDTO clinicaRequestDTO)
    {
        Clinica clinicaConvertida = clinicaMapper.requestRecordToClinica(clinicaRequestDTO);
        Clinica clinicaCriada = clinicaRepository.save(clinicaConvertida);
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponseDto(clinicaCriada);
        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clinicaId}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adiciona endereco a uma clínica existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereo adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ClinicaResponseDTO> addEnderecoToClinica(
            @PathVariable Long clinicaId,
            @RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        // Verificar se o usuário existe
        Clinica clinica = clinicaRepository.findById(clinicaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada"));

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
        clinica.setEndereco(endereco);

        // Salvar o usuário com o novo endereço
        clinicaRepository.save(clinica);

        // Mapear o usuário salvo para o DTO de resposta
        ClinicaResponseDTO clinicaResponse = clinicaMapper.clinicaToResponseDto(clinica);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(clinicaResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicaResponseDTO> readUsuario(@PathVariable Long id) {
        Optional<Clinica> clinicaSalva = clinicaRepository.findById(id);
        if (clinicaSalva.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ClinicaResponseDTO clinicaResponseDTO = clinicaMapper.clinicaToResponseDto(clinicaSalva.get());

        return new ResponseEntity<>(clinicaResponseDTO, HttpStatus.OK);
    }
}
