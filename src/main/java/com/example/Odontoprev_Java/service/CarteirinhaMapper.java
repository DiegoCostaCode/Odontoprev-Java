package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.carteirinha.CarteirinhaResponseDTO;
import com.example.Odontoprev_Java.DTO.carteirinha.CarterinhaRequestDTO;
import com.example.Odontoprev_Java.Model.Carteirinha;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CarteirinhaMapper {

    public Carteirinha requestRecordToCarteirinha(@Valid CarterinhaRequestDTO carteirinhaRequestDTO)
    {
        Carteirinha carteirinha = new Carteirinha();

        carteirinha.setUsuario(carteirinhaRequestDTO.usuarioId());
        carteirinha.setEmissao(carteirinhaRequestDTO.emissao());
        carteirinha.setValidade(carteirinhaRequestDTO.validade());
        carteirinha.setPlano(carteirinhaRequestDTO.planoId());
        return carteirinha;
    }

    public CarteirinhaResponseDTO carteirinhaResponseDTO(Carteirinha carteirinha)
    {
        return new CarteirinhaResponseDTO(
                carteirinha.getId(),
                carteirinha.getEmissao(),
                carteirinha.getValidade(),
                carteirinha.getUsuario(),
                carteirinha.getPlano()
        );
    }
}
