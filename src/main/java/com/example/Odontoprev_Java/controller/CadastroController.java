package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteRequestDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("clinicaDTO", new ClinicaRequestDTO());

        model.addAttribute("pacienteDTO", new PacienteRequestDTO());

        return "userCadaster";
    }

}
