package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.doutor.DoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.doutor.DoutorResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.Model.Doutor;
import com.example.Odontoprev_Java.Model.Endereco.Endereco;
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

    @Autowired
    private DoutorMapper doutorMapper;

    @PostMapping(value = "/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoutorResponseDTO> cadastrarDoutor(
            @Valid @RequestBody DoutorRequestDTO doutorRequestDTO){

        Doutor doutor = doutorMapper.doutorToRequest(doutorRequestDTO);
        Doutor doutorCriado = doutorRepository.save(doutor);
        DoutorResponseDTO doutorResponse = doutorMapper.doutorToResponse(doutorCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(doutorResponse);
    }

    @GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoutorResponseDTO> buscarDoutor(@PathVariable Long id){
        Optional<Doutor> doutor = doutorRepository.findById(id);
        if(doutor.isPresent()){
            DoutorResponseDTO doutorResponse = doutorMapper.doutorToResponse(doutor.get());
            return ResponseEntity.ok(doutorResponse);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doutor não encontrado");
    }

    @PutMapping(value = "{idDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoutorResponseDTO> atualizarDoutor(@PathVariable Long idDoutor, @Valid @RequestBody DoutorRequestDTO doutorRequestDTO){
        Doutor doutor = doutorRepository.findById(idDoutor)
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));

        doutor.setNome(doutorRequestDTO.nome());
        doutor.setCPF(doutorRequestDTO.CPF());
        doutor.setCRM(doutorRequestDTO.CRM());

        Doutor doutorAtualizado = doutorRepository.save(doutor);
        DoutorResponseDTO doutorResponseDTO = doutorMapper.doutorToResponse(doutorAtualizado);
        return ResponseEntity.ok(doutorResponseDTO);
    }

    @DeleteMapping(value = "/{idDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletarDoutor(@PathVariable Long idDoutor){
        Doutor doutor = doutorRepository.findById(idDoutor)
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));
        doutorRepository.delete(doutor);
        return ResponseEntity.noContent().build();
    }

}
