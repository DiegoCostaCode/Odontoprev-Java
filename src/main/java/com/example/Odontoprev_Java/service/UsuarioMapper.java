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
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setCpf(usuarioRequestDto.cpf());
        usuario.setTelefone(usuarioRequestDto.telefone());
        usuario.setCarteirinha(usuarioRequestDto.carteirinha());
        usuario.setEndereco(usuarioRequestDto.endereco());
        return usuario;
    }

    public UsuarioResponseDto usuarioToResponseDto(Usuario usuario)
    {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCarteirinha(),
                usuario.getEndereco()
        );
    }


}
