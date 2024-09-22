package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.CarteirinhaResponseDTO;
import com.example.Odontoprev_Java.Model.Carteirinha;
import org.springframework.stereotype.Service;

@Service
public class CarteirinhaMapper {

    public Carteirinha requestRecordToCarteirinha(CarteirinhaResponseDTO carteirinhaResponseDTO)
    {
        Carteirinha carteirinha = new Carteirinha();

        carteirinha.setUsuario(carteirinhaResponseDTO.usuario());
        carteirinha.setPlano(carteirinhaResponseDTO.plano());
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
