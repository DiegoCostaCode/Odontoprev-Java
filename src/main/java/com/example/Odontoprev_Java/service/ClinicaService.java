package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.ClinicaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Clinica saveClinica(ClinicaRequestDTO clinicaRequestDTO)
    {
        Usuario usuario = usuarioService.saveUsuarioFromClinica(clinicaRequestDTO);

        Clinica clinica = requestToClinica(clinicaRequestDTO);
        clinica.setUsuario(usuario);

        return clinicaRepository.save(clinica);
    }

    @Transactional
    public Clinica updateClinica(ClinicaRequestDTO clinicaRequestDTO, Long id) {
        Clinica clinica = findById(id);

        if (clinica == null) {
            return null;
        }

        BeanUtils.copyProperties(clinicaRequestDTO, clinica);

        Usuario usuario = usuarioService.updateUsuarioFromClinica(clinicaRequestDTO, clinica.getUsuario().getId());
        clinica.setUsuario(usuario);

        return clinicaRepository.save(clinica);
    }

    public Clinica findById(Long id)
    {
        Optional<Clinica> clinica = clinicaRepository.findById(id);

        return clinica.orElse(null);
    }

    public List<Clinica> findAll()
    {
        return clinicaRepository.findAll();
    }

    public void deletarClinica(Long id)
    {
        clinicaRepository.deleteById(id);
    }
}
