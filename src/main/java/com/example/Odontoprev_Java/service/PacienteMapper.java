package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.usuario.UsuarioRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteMapper {


    //Record para Paciente
    public Paciente requestRecordToUsuario(UsuarioRequestDTO usuarioRequestDto)
    {
        Paciente paciente = new Paciente();

        paciente.setNome(usuarioRequestDto.nome());
        paciente.setEmail(usuarioRequestDto.email());
        paciente.setDataNascimento(usuarioRequestDto.dataNascimento());
        paciente.setCpf(usuarioRequestDto.cpf());
        paciente.setTelefone(usuarioRequestDto.telefone());
        return paciente;
    }

    public UsuarioResponseDTO usuarioToResponseDto(Paciente paciente)
    {
        return new UsuarioResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCarteirinha(),
                paciente.getEndereco(),
                paciente.getConsultas()
        );
    }


}
