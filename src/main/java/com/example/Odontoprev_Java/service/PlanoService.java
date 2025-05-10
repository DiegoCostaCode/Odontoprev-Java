package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.planosDTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.planosDTO.PlanoResponseDTO;
import com.example.Odontoprev_Java.model.Plano;
import com.example.Odontoprev_Java.repository.PlanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanosRepository planosRepository;

    public List<Plano> findAll()
    {
        return planosRepository.findAll();
    }

    public List<Plano> findPlanosAtivos()
    {
        return planosRepository.findByStatus("T");
    }

    public Plano findById(Long id)
    {
        return planosRepository.findById(id).orElse(null);
    }

    public Plano savePlano(PlanoRequestDTO planoRequestDTO)
    {
        Plano plano = new Plano();
        plano.setNome(planoRequestDTO.getNome());
        plano.setDescricao(planoRequestDTO.getDescricao());
        plano.setPreco(planoRequestDTO.getPreco());
        plano.setStatus(planoRequestDTO.getAtivo());
        plano.setDataAtualizacao(planoRequestDTO.getDataAtualizacao());

        return planosRepository.save(plano);
    }

    public Plano updatePlano( Long id, PlanoRequestDTO planoRequestDTO)
    {
        Plano plano = findById(id);

        if (plano != null) {

            plano.setNome(planoRequestDTO.getNome());
            plano.setDescricao(planoRequestDTO.getDescricao());
            plano.setPreco(planoRequestDTO.getPreco());
            plano.setStatus(planoRequestDTO.getAtivo());
            plano.setDataAtualizacao(LocalDateTime.now());

            return planosRepository.save(plano);
        }

        return null;
    }

    public Plano ativarPlano(Long id)
    {
        Plano plano = findById(id);

        if (plano != null) {
            plano.setStatus("T");
            return planosRepository.save(plano);
        }

        return null;
    }

    public PlanoResponseDTO planoResponse(Plano plano)
    {
        PlanoResponseDTO planoResponseDTO = new PlanoResponseDTO(
                plano.getId(),
                plano.getNome(),
                plano.getDescricao(),
                plano.getPreco(),
                plano.getStatus(),
                plano.getDataAtualizacao()
        );
        return planoResponseDTO;
    }
}
