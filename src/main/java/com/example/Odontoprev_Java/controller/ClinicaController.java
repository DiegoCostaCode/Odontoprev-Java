package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @PostMapping("/register")
    public String saveClinica(@Valid @ModelAttribute ClinicaRequestDTO clinicaRequestDTO, Model model)
    {
        Clinica clinica = clinicaService.saveClinica(clinicaRequestDTO);

        ModelAndView mv = new ModelAndView("cadasterClinica");

        model.addAttribute("clinicaDTO", clinicaRequestDTO);

        return clinicaGetAll(model);
    }

    @GetMapping(value = "/all/clinicas")
    public String clinicaGetAll(Model model){

        model.addAttribute("clinicas", clinicaService.findAll());

        return "clinicas";
    }

}
