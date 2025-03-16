package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteRequestDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Planos;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.PacienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PlanoService planoService;

    public List<Paciente> findAll()
    {
        return pacienteRepository.findAll();
    }

    public Paciente requestToPaciente(PacienteRequestDTO pacienteRequestDTO)
    {
        Paciente paciente = new Paciente();

        BeanUtils.copyProperties(pacienteRequestDTO, paciente);

        return paciente;
    }

    public PacienteRequestDTO pacienteToRequest(Paciente paciente)
    {

        PacienteRequestDTO pacienteRequest = new PacienteRequestDTO();

        pacienteRequest.setNome(paciente.getNome());
        pacienteRequest.setCpf(paciente.getCpf());
        pacienteRequest.setTelefone(paciente.getTelefone());
        pacienteRequest.setDataNascimento(paciente.getDataNascimento());
        pacienteRequest.setEmail(paciente.getUsuario().getEmail());
        pacienteRequest.setSenha(paciente.getUsuario().getSenha());
        pacienteRequest.setId_plano(paciente.getPlano().getId());

        return pacienteRequest;
    }

    @Transactional
    public Paciente savePaciente(PacienteRequestDTO pacienteRequestDTO)
    {
        Planos plano = planoService.findById(pacienteRequestDTO.getId_plano());

        if (plano == null) {
            throw new IllegalArgumentException("Plano não encontrado");
        }

        Usuario usuario = usuarioService.saveUsuarioOfPaciente(pacienteRequestDTO);

        Paciente paciente = requestToPaciente(pacienteRequestDTO);
        paciente.setUsuario(usuario);
        paciente.setPlano(plano);

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente updatePaciente(PacienteRequestDTO pacienteRequestDTO, Long id) {

        Paciente paciente = findById(id);

        if (paciente == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        Planos plano = planoService.findById(pacienteRequestDTO.getId_plano());

        if (plano == null) {
            throw new IllegalArgumentException("Plano não encontrado");
        }

        BeanUtils.copyProperties(pacienteRequestDTO, paciente);

        Usuario usuario = usuarioService.updateUsuarioOfPaciente(pacienteRequestDTO, paciente.getUsuario().getId());
        paciente.setUsuario(usuario);
        paciente.setPlano(plano);

        return pacienteRepository.save(paciente);
    }

    public Paciente findById(Long id)
    {
        return pacienteRepository.findById(id).orElse(null);
    }

    public void deletarPaciente(Long id)
    {
        pacienteRepository.deleteById(id);
    }




}
