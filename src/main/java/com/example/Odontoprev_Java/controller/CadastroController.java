package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteRequestDTO;
import com.example.Odontoprev_Java.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadastroController {

    @Autowired
    private PlanoService planoService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("clinicaDTO", new ClinicaRequestDTO());

        model.addAttribute("pacienteDTO", new PacienteRequestDTO());

        model.addAttribute("planos", planoService.findAll());

        return "cadastrar_usuario";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
