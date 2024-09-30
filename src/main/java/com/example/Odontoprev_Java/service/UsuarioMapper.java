package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.UsuarioRequestDto;
import com.example.Odontoprev_Java.DTO.UsuarioResponseDto;
import com.example.Odontoprev_Java.Model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {


    //Record para Livro
    public Usuario requestRecordToUsuario(UsuarioRequestDto usuarioRequestDto)
    {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDto.nome());

        return usuario;
    }

    public UsuarioResponseDto usuarioToResponseDto(Usuario usuario)
    {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome()

        );
    }


}
