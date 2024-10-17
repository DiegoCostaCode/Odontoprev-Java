package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.plano.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.PacienteResponseDTO;
import com.example.Odontoprev_Java.Model.Paciente;
import org.springframework.stereotype.Service;

@Service
public class PacienteMapper {

        public Paciente requestToPlano(PacienteRequestDTO pacienteRequestDTO)
        {
            Paciente paciente = new Paciente();

            paciente.setNome(pacienteRequestDTO.nome());
            paciente.setCpf(pacienteRequestDTO.cpf());
            paciente.setDataNascimento(pacienteRequestDTO.dataNascimento());
            paciente.setEmail(pacienteRequestDTO.email());
            paciente.setTelefone(pacienteRequestDTO.telefone());

            return paciente;
        }

        public PacienteResponseDTO pacienteResponseDTO(Paciente paciente)
        {
            return new PacienteResponseDTO(
                    paciente.getId(),
                    paciente.getNome(),
                    paciente.getDataNascimento(),
                    paciente.getCpf(),
                    paciente.getEmail(),
                    paciente.getTelefone(),
                    paciente.getEndereco()
            );
        }


}
