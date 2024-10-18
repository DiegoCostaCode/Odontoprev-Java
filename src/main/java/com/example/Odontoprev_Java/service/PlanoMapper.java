package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.plano.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.plano.PlanoResponseDTO;
import com.example.Odontoprev_Java.Model.Plano;
import org.springframework.stereotype.Service;

@Service
public class PlanoMapper {

        public Plano requestToPlano(PlanoRequestDTO planoRequestDTO)
        {
            Plano plano = new Plano();

            plano.setNome(planoRequestDTO.nome());
            plano.setDescricao(planoRequestDTO.descricao());
            plano.setPreco(planoRequestDTO.preco());
            plano.setAtivo(planoRequestDTO.ativo());

            return plano;
        }

        public PlanoResponseDTO planoResponseDTO(Plano plano)
        {
            return new PlanoResponseDTO(
                    plano.getId_plano(),
                    plano.getNome(),
                    plano.getDescricao(),
                    plano.getPreco(),
                    plano.isAtivo()
            );
        }
}
