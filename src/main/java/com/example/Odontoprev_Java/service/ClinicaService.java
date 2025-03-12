package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.ClinicaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Clinica requestToClinica(ClinicaRequestDTO clinicaRequestDTO)
    {
        Clinica clinica = new Clinica();

        BeanUtils.copyProperties(clinicaRequestDTO, clinica);

        return clinica;
    }

    public ClinicaRequestDTO clinicaToRequest(Clinica clinica)
    {
        ClinicaRequestDTO clinicaRequestDTO = new ClinicaRequestDTO();

        BeanUtils.copyProperties(clinica, clinicaRequestDTO);

        return clinicaRequestDTO;
    }

    public Clinica saveClinica(ClinicaRequestDTO clinicaRequestDTO)
    {
        Usuario usuario = usuarioService.saveUsuarioFromClinica(clinicaRequestDTO);

        Clinica clinica = requestToClinica(clinicaRequestDTO);
        clinica.setUsuario(usuario);

        return clinicaRepository.save(clinica);
    }

    public List<Clinica> findAll()
    {
        return clinicaRepository.findAll();
    }


}
