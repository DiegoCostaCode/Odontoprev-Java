package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.auditorDTO.AuditorRequestDTO;
import com.example.Odontoprev_Java.DTO.auditorDTO.AuditorResponseDTO;
import com.example.Odontoprev_Java.Model.Auditor;
import com.example.Odontoprev_Java.Model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.AuditorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuditorService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuditorRepository auditorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Métodos de Persistência

    @Transactional
    public Auditor saveAuditor(AuditorRequestDTO auditorRequestDTO)
    {
        Auditor auditor = requestToAuditor(auditorRequestDTO);

        Usuario usuario = usuarioService.save(
                auditorRequestDTO.getEmail(),
                auditorRequestDTO.getSenha(),
                auditorRequestDTO.getTipo());

        auditor.setUsuario(usuario);

        return auditorRepository.save(auditor);
    }

    //Métodos de CRUD
    public Auditor findAuditorById(long id)
    {
        return auditorRepository.findById(id).orElse(null);
    }

    public List<Auditor> findAll()
    {
        return auditorRepository.findAll();
    }

    //Mappers
    public Auditor requestToAuditor(AuditorRequestDTO auditorRequestDTO)
    {
        Auditor auditor = new Auditor();

        BeanUtils.copyProperties(auditorRequestDTO, auditor);

        return auditor;
    }

    public AuditorRequestDTO auditorToRequest(Auditor auditor)
    {

        AuditorRequestDTO auditorRequest = new AuditorRequestDTO();

        auditorRequest.setNome(auditor.getNome());
        auditorRequest.setEmail(auditor.getUsuario().getEmail());
        auditorRequest.setTelefone(auditor.getTelefone());
        auditorRequest.setSenha(auditor.getUsuario().getSenha());

        return auditorRequest;
    }

    public AuditorResponseDTO auditorResponse(Auditor auditor)
    {
        AuditorResponseDTO auditorResponseDTO = new AuditorResponseDTO(
                auditor.getId(),
                auditor.getNome(),
                auditor.getTelefone(),
                auditor.getUsuario().getEmail(),
                auditor.getUsuario().getId()
        );

        return auditorResponseDTO;
    }

}
