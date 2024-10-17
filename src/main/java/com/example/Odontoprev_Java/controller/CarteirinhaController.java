//package com.example.Odontoprev_Java.controller;
//
//import com.example.Odontoprev_Java.DTO.usuario.PacienteResponseDTO;
//import com.example.Odontoprev_Java.Model.Paciente;
//import com.example.Odontoprev_Java.Repository.PacienteRepository;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping(value = "/carteirinha", produces = {"aplication/json"})
//@Tag(name = "api-carteirinha")
//public class CarteirinhaController {
//
//
//    @Autowired
//    private PacienteRepository pacienteRepository;
//
////    @Autowired(required = true)
////    private CarteirinhaMapper carteirinhaMapper;
////
////    @Autowired(required = true)
////    private PacienteMapper pacienteMapper;
//
//    @Operation(summary = "Atendimento usuário pelo ID de usuário")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
//            @ApiResponse(responseCode = "204", description = "Usuário não encontrado"),
//            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
//    })
//    @GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PacienteResponseDTO> readUsuario(@PathVariable Long usuarioId) {
//        Optional<Paciente> usuarioSalvo = pacienteRepository.findById(usuarioId);
//        if (usuarioSalvo.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        PacienteResponseDTO usuarioResponse = pacienteMapper.usuarioToResponseDto(usuarioSalvo.get());
//
//        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
//    }
//}
