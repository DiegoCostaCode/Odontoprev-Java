package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaResponseDTO;
import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.service.PacienteService;
import com.example.Odontoprev_Java.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PlanoService planoService;

    @GetMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> getPaciente(@PathVariable Long id) {

        Paciente paciente = pacienteService.findById(id);

        if (paciente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PacienteResponseDTO pacienteResponseDTO = pacienteService.pacienteResponse(paciente);

        return new ResponseEntity<>(pacienteResponseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> createPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {

//        PacienteResponseDTO pacienteResponseDTO = pacienteService.inserirOdontoPaciente(pacienteRequestDTO);

        Paciente paciente = pacienteService.savePaciente(pacienteRequestDTO);

        PacienteResponseDTO pacienteResponseDTO = pacienteService.pacienteResponse(paciente);

        return new ResponseEntity<>(pacienteResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteRequestDTO pacienteRequestDTO) {

//        PacienteResponseDTO pacienteResponseDTO = pacienteService.updateOdontoPaciente(pacienteRequestDTO, id);

        Paciente paciente = pacienteService.updatePaciente(pacienteRequestDTO, id);

        PacienteResponseDTO pacienteResponseDTO = pacienteService.pacienteResponse(paciente);

        return new ResponseEntity<>(pacienteResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseDTO> deletePaciente(@PathVariable Long id) {

//        pacienteService.deleteOdontoPaciente(id);

        pacienteService.deletarPaciente(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public String pacienteGetAllView(Model model){

        model.addAttribute("pacientes", pacienteService.findAll());

        return "pacientes";
    }

    @GetMapping(value = "/edit/{id}")
    public String clinicaUpdateView(@PathVariable Long id, Model model){

        Paciente paciente = pacienteService.findById(id);

        if(paciente == null)
        {
            return pacienteGetAllView(model);
        }

        PacienteRequestDTO pacienteRequestDTO = pacienteService.pacienteToRequest(paciente);


        model.addAttribute("pacienteRequest", pacienteRequestDTO);
        model.addAttribute("idPaciente", paciente.getId());
        model.addAttribute("planos", planoService.findAll());

        return "updateProfilePaciente";
    }

    @PostMapping("/register")
    public String savePaciente(@Valid @ModelAttribute PacienteRequestDTO pacienteRequestDTO, Model model)
    {

        Paciente paciente = pacienteService.savePaciente(pacienteRequestDTO);

        model.addAttribute("pacienteDTO", pacienteRequestDTO);

        return "redirect:/paciente/all";
    }
    @PostMapping(value = "/update/{id}")
    public String pacienteUpdate(@PathVariable Long id, @Valid PacienteRequestDTO pacienteRequestDTO, Model model){
        Paciente paciente = pacienteService.updatePaciente(pacienteRequestDTO,id);
        return pacienteGetAllView(model);
    }

    @GetMapping(value = "/delete/{id}")
    public String pacienteDelete(@PathVariable long id, Model model){
        pacienteService.deletarPaciente(id);
        return pacienteGetAllView(model);
    }

}
