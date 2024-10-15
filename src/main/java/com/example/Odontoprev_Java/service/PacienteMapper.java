package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.usuario.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteMapper {


    //Record para Paciente
    public Paciente requestRecordToUsuario(PacienteRequestDTO pacienteRequestDto)
    {
        Paciente paciente = new Paciente();

        paciente.setNome(pacienteRequestDto.nome());
        paciente.setEmail(pacienteRequestDto.email());
        paciente.setDataNascimento(pacienteRequestDto.dataNascimento());
        paciente.setCpf(pacienteRequestDto.cpf());
        paciente.setTelefone(pacienteRequestDto.telefone());
        return paciente;
    }

    public PacienteResponseDTO usuarioToResponseDto(Paciente paciente)
    {
        return new PacienteResponseDTO(
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
