package com.example.Odontoprev_Java.controller;

import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoPacienteRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.example.Odontoprev_Java.Model.usuario.UsuarioDetails;
import com.example.Odontoprev_Java.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private ProcedimentoService procedimentoService;


    @GetMapping(value = "/")
    public String agendar(@AuthenticationPrincipal UsuarioDetails usuarioDetails, Model model) {

        Usuario usuario = usuarioDetails.getUsuario();

        if (usuario.getTipo().getDescricao().equals("clinica")) {

            Clinica clinica = clinicaService.findByCredenciais(usuario.getId());

            model.addAttribute("tipo", "clinica");
            model.addAttribute("listaAgendamentos",agendamentoService.findAgendamentoByClinicaId(clinica.getId()));
            model.addAttribute("agendamentoClinicaDTO", new AgendamentoClinicaRequestDTO());

            return "agendamentos";
        }
        else if (usuario.getTipo().getDescricao().equals("paciente")) {

            Paciente paciente = pacienteService.findByCredenciais(usuario.getId());

            AgendamentoPacienteRequestDTO agendamentoPacienteDTO = new AgendamentoPacienteRequestDTO();
            agendamentoPacienteDTO.setPacienteId(paciente.getId());

            model.addAttribute("tipo", "paciente");
            model.addAttribute("id", paciente.getId());
            model.addAttribute("pacienteNome", paciente.getNome());
            model.addAttribute("clinicas", clinicaService.findAll());
            model.addAttribute("procedimentos", procedimentoService.findProcedimentosAtivos());
            model.addAttribute("listaAgendamentos",agendamentoService.findAgendamentoByPacienteId(paciente.getId()));
            model.addAttribute("agendamentoPacienteDTO", agendamentoPacienteDTO);

            return "agendamentos";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/register")
    public String efetuarAgendamento(@AuthenticationPrincipal UsuarioDetails usuarioDetails,
                                     @Valid @ModelAttribute AgendamentoPacienteRequestDTO agendamentoPacienteRequestDTO,
                                     Model model) {

        Long usuarioIdLogado = usuarioDetails.getUsuario().getId();
        Long pacienteIdDoForm = agendamentoPacienteRequestDTO.getPacienteId();

        if (!pacienteService.findByCredenciais(usuarioIdLogado).getId().equals(pacienteIdDoForm)) {
            return "redirect:/erro/403";
        }

        agendamentoService.save(agendamentoPacienteRequestDTO);

        return "redirect:/agendamentos/";
    }

    @GetMapping(value = "/update/{idAgendamento}/clinica/")
    public String finalizarAgendamento(@PathVariable Long idAgendamento,
                                     @Valid @ModelAttribute AgendamentoClinicaRequestDTO agendamentoClinicaRequestDTO) {

        agendamentoService.clinicaUpdateAgendamento(idAgendamento, agendamentoClinicaRequestDTO);

        return "redirect:/agendamentos/";
    }

}
