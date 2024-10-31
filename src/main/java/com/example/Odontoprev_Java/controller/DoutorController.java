package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.doutor.DoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.doutor.DoutorResponseDTO;
import com.example.Odontoprev_Java.DTO.endereco.EnderecoRequestDTO;
import com.example.Odontoprev_Java.DTO.paciente.PacienteResponseDTO;
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
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/doutor", produces = {"aplication/json"})
@Tag(name = "api-doutor")
public class DoutorController {

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private DoutorMapper doutorMapper;

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoutorResponseDTO> createDoutor(
            @Valid @RequestBody DoutorRequestDTO doutorRequestDTO){

        Doutor doutor = doutorMapper.doutorToRequest(doutorRequestDTO);
        Doutor doutorCriado = doutorRepository.save(doutor);
        DoutorResponseDTO doutorResponse = doutorMapper.doutorToResponse(doutorCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(doutorResponse);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<DoutorResponseDTO>>> readDoutor(){
        List<Doutor> doutores = doutorRepository.findAll();
        List<EntityModel<DoutorResponseDTO>> doutoresResponse = doutores.stream()
                .map(doutor -> {
                    DoutorResponseDTO doutorResponse = doutorMapper.doutorToResponse(doutor);
                    return EntityModel.of(doutorResponse,
                            linkTo(methodOn(DoutorController.class).readDoutor()).withSelfRel(),
                            linkTo(methodOn(DoutorController.class).createDoutor(null)).withRel("post"),
                            linkTo(methodOn(DoutorController.class).readDoutorById(doutor.getId())).withRel("get"),
                            linkTo(methodOn(DoutorController.class).updateDoutor(doutor.getId(), null)).withRel("put"),
                            linkTo(methodOn(DoutorController.class).deleteDoutor(doutor.getId())).withRel("delete"));
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(doutoresResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{idDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoutorResponseDTO> readDoutorById(@PathVariable Long idDoutor){
        Optional<Doutor> doutor = doutorRepository.findById(idDoutor);
        if(doutor.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DoutorResponseDTO doutorResponse = doutorMapper.doutorToResponse(doutor.get());


        return new ResponseEntity<>(doutorResponse, HttpStatus.OK);
    }

    @PutMapping(value = "{idDoutor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoutorResponseDTO> updateDoutor(@PathVariable Long idDoutor, @Valid @RequestBody DoutorRequestDTO doutorRequestDTO){
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
    public ResponseEntity<Void> deleteDoutor(@PathVariable Long idDoutor){
        Doutor doutor = doutorRepository.findById(idDoutor)
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado"));
        doutorRepository.delete(doutor);
        return ResponseEntity.noContent().build();
    }

}
