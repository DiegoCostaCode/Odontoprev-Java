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

    @Autowired(required = true)
    private DoutorMapper doutorMapper;


}
