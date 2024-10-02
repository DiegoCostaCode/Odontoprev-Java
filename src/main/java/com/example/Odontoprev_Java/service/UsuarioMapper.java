package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.usuario.UsuarioRequestDTO;
import com.example.Odontoprev_Java.DTO.usuario.UsuarioResponseDTO;
import com.example.Odontoprev_Java.Model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {


    //Record para Usuario
    public Usuario requestRecordToUsuario(UsuarioRequestDTO usuarioRequestDto)
    {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setDataNascimento(usuarioRequestDto.dataNascimento());
        usuario.setCpf(usuarioRequestDto.cpf());
        usuario.setTelefone(usuarioRequestDto.telefone());
        return usuario;
    }

    public UsuarioResponseDTO usuarioToResponseDto(Usuario usuario)
    {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCarteirinha(),
                usuario.getEndereco(),
                usuario.getConsultas()
        );
    }


}
