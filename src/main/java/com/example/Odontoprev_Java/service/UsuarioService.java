package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.usuario.Enum_tipo_usuario;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuarioFromClinica(ClinicaRequestDTO clinicaRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(clinicaRequestDTO.getEmail());
        usuario.setSenha(clinicaRequestDTO.getSenha());
        usuario.setTipo(Enum_tipo_usuario.CLINICA);
        usuario.setDataCadastramento(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }
}
