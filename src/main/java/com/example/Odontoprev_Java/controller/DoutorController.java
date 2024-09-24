package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.DoutorRequestDTO;
import com.example.Odontoprev_Java.DTO.DoutorResponseDTO;
import com.example.Odontoprev_Java.Model.Doutor;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
