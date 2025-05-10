package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.usuarioDTO.UsuarioAuthDTO;
import com.example.Odontoprev_Java.DTO.usuarioDTO.UsuarioAuthResponseDTO;
import com.example.Odontoprev_Java.model.Clinica;
import com.example.Odontoprev_Java.model.Paciente;
import com.example.Odontoprev_Java.model.usuario.Enum_tipo_usuario;
import com.example.Odontoprev_Java.model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.ClinicaRepository;
import com.example.Odontoprev_Java.repository.PacienteRepository;
import com.example.Odontoprev_Java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario save(String email, String senha, Enum_tipo_usuario tipo) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setTipo(tipo);
        usuario.setDataCadastramento(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, String email, String senha, Enum_tipo_usuario tipo) {
        Usuario usuario = findById(id);

        if (usuario == null) {
            return null;
        }

        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setTipo(tipo);

        return usuarioRepository.save(usuario);
    }

    public Usuario findById(long id)
    {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.orElse(null);
    }

    public Usuario findByEmail(String email)
    {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        return usuario.orElse(null);
    }

    public UsuarioAuthResponseDTO login(UsuarioAuthDTO usuarioAuthDTO) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioAuthDTO.getEmail());

        if (usuario.isPresent() && passwordEncoder.matches(usuarioAuthDTO.getSenha(), usuario.get().getSenha())) {

            Optional<Clinica> clinica = clinicaRepository.findByUsuario(usuario.get());
            if (clinica.isPresent()) {
                return new UsuarioAuthResponseDTO(clinica.get().getId(), clinica.get().getUsuario().getTipo().toString());
            }

            Optional<Paciente> paciente = pacienteRepository.findByUsuario(usuario.get());
            if (paciente.isPresent()) {
                return new UsuarioAuthResponseDTO(paciente.get().getId(), paciente.get().getUsuario().getTipo().toString());
            }
        }
        return null;
    }
}
