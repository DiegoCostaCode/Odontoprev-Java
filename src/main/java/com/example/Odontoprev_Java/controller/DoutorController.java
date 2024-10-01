package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.DoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.DoutorResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Endereco;
import com.example.Odontoprev_Java.Repository.DoutorRepository;
import com.example.Odontoprev_Java.service.DoutorMapper;
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
@RequestMapping(value = "/doutor", produces = {"aplication/json"})
@Tag(name = "api-doutor")
public class DoutorController {

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired(required = true)
    private DoutorMapper doutorMapper;

    @Operation(summary = "Registra um Doutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doutor registrado!"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos", content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DoutorResponseDTO> createDoutor(@Valid @RequestBody DoutorRequestDTO doutorRequestDTO)
    {
        Doutor doutorConvertida = doutorMapper.requestRecordToDoutor(doutorRequestDTO);
        Doutor doutorCriada = doutorRepository.save(doutorConvertida);
        DoutorResponseDTO doutorResponseDTO = doutorMapper.doutorToResponseDto(doutorCriada);

        return new ResponseEntity<>(doutorResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{doutorId}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adiciona endereco a um doutor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Doutor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<DoutorResponseDTO> addEnderecoToDoutor(
            @PathVariable Long doutorId,
            @RequestBody EnderecoRequestDTO enderecoRequestDTO) {

        // Verificar se o usuário existe
        Doutor doutor = doutorRepository.findById(doutorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doutor não encontrada"));

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
        doutor.setEndereco(endereco);

        // Salvar o usuário com o novo endereço
        doutorRepository.save(doutor);

        // Mapear o usuário salvo para o DTO de resposta
        DoutorResponseDTO doutorResponseDTO = doutorMapper.doutorToResponseDto(doutor);

        // Retornar o usuário atualizado
        return new ResponseEntity<>(doutorResponseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoutorResponseDTO> readUsuario(@PathVariable Long id) {
        Optional<Doutor> doutorSalvo = doutorRepository.findById(id);
        if (doutorSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DoutorResponseDTO doutorResponseDTO = doutorMapper.doutorToResponseDto(doutorSalvo.get());

        return new ResponseEntity<>(doutorResponseDTO, HttpStatus.OK);
    }
}
