package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteRequestDTO;
import com.example.Odontoprev_Java.Model.usuario.Enum_tipo_usuario;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuarioOfClinica(ClinicaRequestDTO clinicaRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(clinicaRequestDTO.getEmail());
        usuario.setSenha(clinicaRequestDTO.getSenha());
        usuario.setTipo(Enum_tipo_usuario.CLINICA);
        usuario.setDataCadastramento(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario saveUsuarioOfPaciente(PacienteRequestDTO pacienteRequestDTO) {
        Usuario usuario = new Usuario();

        usuario.setEmail(pacienteRequestDTO.getEmail());
        usuario.setSenha(pacienteRequestDTO.getSenha());
        usuario.setTipo(Enum_tipo_usuario.PACIENTE);
        usuario.setDataCadastramento(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuarioOfPaciente(PacienteRequestDTO pacienteRequestDTO, Long id) {
        Usuario usuario = findById(id);

        if (usuario == null) {
            return null;
        }

        usuario.setEmail(pacienteRequestDTO.getEmail());
        usuario.setSenha(pacienteRequestDTO.getSenha());
        usuario.setTipo(Enum_tipo_usuario.PACIENTE);

        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuarioOfClinica(ClinicaRequestDTO clinicaRequestDTO, Long id) {
        Usuario usuario = findById(id);

        if (usuario == null) {
            return null;
        }

        usuario.setEmail(clinicaRequestDTO.getEmail());
        usuario.setSenha(clinicaRequestDTO.getSenha());
        usuario.setTipo(Enum_tipo_usuario.CLINICA);

        return usuarioRepository.save(usuario);
    }

    public Usuario findById(long id)
    {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.orElse(null);
    }

}
