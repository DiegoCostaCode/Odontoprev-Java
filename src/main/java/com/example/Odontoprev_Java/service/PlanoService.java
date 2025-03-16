package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.Model.Paciente;
import com.example.Odontoprev_Java.Model.Planos;
import com.example.Odontoprev_Java.repository.PlanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanosRepository planosRepository;

    public List<Planos> findAll()
    {
        return planosRepository.findAll();
    }

    public  Planos findById(Long id)
    {
        return planosRepository.findById(id).orElse(null);
    }


}
