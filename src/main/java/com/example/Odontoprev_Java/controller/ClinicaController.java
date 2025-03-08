package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping(value = "/")
    public ModelAndView clinicaCadasterView()
    {
        ModelAndView mv = new ModelAndView("cadasterClinica");

        mv.addObject("clinicaDTO", new ClinicaRequestDTO());

        return mv;
    }

    @PostMapping("/register")
    public ModelAndView saveClinica(@Valid @ModelAttribute ClinicaRequestDTO clinicaRequestDTO)
    {
        Clinica clinica = clinicaService.saveClinica(clinicaRequestDTO);

        ModelAndView mv = new ModelAndView("cadasterClinica");

        mv.addObject("clinicaDTO", clinicaRequestDTO);

        return clinicaGetAll();
    }

    @GetMapping(value = "/clinicas")
    public ModelAndView clinicaGetAll(){
        ModelAndView mv = new ModelAndView("allClinicas");

        mv.addObject("clinicas", clinicaService.findAll());

        return mv;
    }

}
