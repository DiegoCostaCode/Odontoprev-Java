package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @GetMapping(value = "/all/clinicas")
    public String clinicaGetAllView(Model model){

        model.addAttribute("clinicas", clinicaService.findAll());

        return "clinicas";
    }

    @GetMapping(value = "/update/{id}")
    public String clinicaUpdateView(@PathVariable Long id, Model model){

        Clinica clinica = clinicaService.findById(id);

        if(clinica == null)
        {
            return clinicaGetAllView(model);
        }

        model.addAttribute("clinicaInfos", clinica);

        return "updateProfile";
    }

    @PostMapping("/register")
    public String saveClinica(@Valid @ModelAttribute ClinicaRequestDTO clinicaRequestDTO, Model model)
    {
        Clinica clinica = clinicaService.saveClinica(clinicaRequestDTO);

        ModelAndView mv = new ModelAndView("cadasterClinica");

        model.addAttribute("clinicaDTO", clinicaRequestDTO);

        return "redirect:/clinica/all/clinicas";
    }

    @PostMapping(value = "/edit/{id}")
    public String clinicaUpdate(@PathVariable Long id, @Valid ClinicaRequestDTO clinicaRequestDTO, Model model){
        Clinica clinica = clinicaService.updateClinica(clinicaRequestDTO,id);
        return clinicaGetAllView(model);
    }

    @GetMapping(value = "/delete/{id}")
    public String clinicaDelete(@PathVariable Long id, Model model){
        clinicaService.deletarClinica(id);
        return clinicaGetAllView(model);
    }

}
