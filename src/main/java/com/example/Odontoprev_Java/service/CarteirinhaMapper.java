package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.carteirinha.CarteirinhaResponseDTO;
import com.example.Odontoprev_Java.DTO.carteirinha.CarterinhaRequestDTO;
import com.example.Odontoprev_Java.Model.Carteirinha;
import org.springframework.stereotype.Service;

@Service
public class CarteirinhaMapper {

    public Carteirinha requestToCarteirinha(CarterinhaRequestDTO carteirinhaRequestDTO)
    {
        Carteirinha carteirinha = new Carteirinha();

        carteirinha.setPaciente(carteirinhaRequestDTO.paciente());
        carteirinha.setPlano(carteirinhaRequestDTO.plano());

        return carteirinha;
    }

    public CarteirinhaResponseDTO carteirinhaResponseDTO(Carteirinha carteirinha)
    {
        return new CarteirinhaResponseDTO(
                carteirinha.getId_carteirinha(),
                carteirinha.getNumero(),
                carteirinha.getEmissao(),
                carteirinha.getValidade(),
                carteirinha.getPaciente(),
                carteirinha.getPlano()
        );
    }
}
