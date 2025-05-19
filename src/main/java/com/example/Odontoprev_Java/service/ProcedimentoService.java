package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.procedimentoDTO.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimentoDTO.ProcedimentoResponseDTO;
import com.example.Odontoprev_Java.model.Plano;
import com.example.Odontoprev_Java.model.procedimento.Procedimento;
import com.example.Odontoprev_Java.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    public List<Procedimento> findAll() {
        return procedimentoRepository.findAll();
    }

    public List<Procedimento> findProcedimentosAtivos() {
        return procedimentoRepository.findByStatus("T");
    }

    public Procedimento findById(Long id) {
        return procedimentoRepository.findById(id).orElse(null);
    }

    public ProcedimentoResponseDTO procedimentoResponse(Procedimento procedimento) {

        ProcedimentoResponseDTO procedimentoResponseDTO = new ProcedimentoResponseDTO(
                procedimento.getId(),
                procedimento.getTitulo().getDescricao(),
                procedimento.getDescricao(),
                procedimento.getValorCobertura(),
                procedimento.getDataAtualizacao(),
                procedimento.getStatus()
        );

        return procedimentoResponseDTO;
    }

    public Procedimento save(ProcedimentoRequestDTO procedimentoRequestDTO)
    {
        Procedimento procedimento = new Procedimento();

        procedimento.setTitulo(procedimentoRequestDTO.getTitulo());
        procedimento.setDescricao(procedimentoRequestDTO.getDescricao());
        procedimento.setValorCobertura(procedimentoRequestDTO.getValorCobertura());
        procedimento.setDataAtualizacao(LocalDateTime.now());

        return procedimentoRepository.save(procedimento);
    }

    public Procedimento update(Long id,ProcedimentoRequestDTO procedimentoRequestDTO)
    {
        Procedimento procedimento = findById(id);

        if (procedimento == null)
        {
            return null;
        }

        procedimento.setTitulo(procedimentoRequestDTO.getTitulo());
        procedimento.setValorCobertura(procedimentoRequestDTO.getValorCobertura());
        procedimento.setDescricao(procedimentoRequestDTO.getDescricao());
        procedimento.setDataAtualizacao(LocalDateTime.now());
        procedimento.setStatus("F");

        return procedimentoRepository.save(procedimento);
    }

    public Procedimento ativarProc(Long id)
    {
        Procedimento proc = findById(id);

        if (proc != null) {
            proc.setStatus("T");
            return procedimentoRepository.save(proc);
        }

        return null;
    }

}
