package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteResponseDTO;
import com.example.Odontoprev_Java.model.Paciente;
import com.example.Odontoprev_Java.model.Plano;
import com.example.Odontoprev_Java.model.usuario.Enum_tipo_usuario;
import jakarta.persistence.EntityManager;
import com.example.Odontoprev_Java.model.usuario.Usuario;
import com.example.Odontoprev_Java.repository.PacienteRepository;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PlanoService planoService;

    //Métodos de CRUD
    @Transactional
    public Paciente savePaciente(PacienteRequestDTO pacienteRequestDTO)
    {
        Plano plano = planoService.findById(pacienteRequestDTO.getIdPlano());

        if (plano == null) {
            throw new IllegalArgumentException("Plano não encontrado");
        }

        Usuario usuario = usuarioService.save(
                pacienteRequestDTO.getEmail(),
                pacienteRequestDTO.getSenha(),
                pacienteRequestDTO.getTipo());

        Paciente paciente = requestToPaciente(pacienteRequestDTO);
        paciente.setUsuario(usuario);
        paciente.setPlano(plano);

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente updatePaciente(PacienteRequestDTO pacienteRequestDTO, Long id) {

        Paciente paciente = findById(id);

        if (paciente == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        Plano plano = planoService.findById(pacienteRequestDTO.getIdPlano());

        if (plano == null) {
            throw new IllegalArgumentException("Plano não encontrado");
        }

        BeanUtils.copyProperties(pacienteRequestDTO, paciente);

        Usuario usuario = usuarioService.update(
                paciente.getUsuario().getId(),
                pacienteRequestDTO.getEmail(),
                pacienteRequestDTO.getSenha(),
                pacienteRequestDTO.getTipo());


        paciente.setUsuario(usuario);
        paciente.setPlano(plano);

        return pacienteRepository.save(paciente);
    }

    public void deletarPaciente(Long id)
    {
        pacienteRepository.deleteById(id);
    }

    //Métodos de busca
    public List<Paciente> findAll()
    {
        return pacienteRepository.findAll();
    }

    public Paciente findById(Long id)
    {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente findByCredenciais(Long id)
    {
        Optional<Paciente> paciente = pacienteRepository.findByUsuarioId(id);

        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            return null;
        }
    }

    //Mappers
    public Paciente requestToPaciente(PacienteRequestDTO pacienteRequestDTO)
    {
        Paciente paciente = new Paciente();

        BeanUtils.copyProperties(pacienteRequestDTO, paciente);

        return paciente;
    }

    public PacienteRequestDTO pacienteToRequest(Paciente paciente)
    {

        PacienteRequestDTO pacienteRequest = new PacienteRequestDTO();

        pacienteRequest.setNome(paciente.getNome());
        pacienteRequest.setCpf(paciente.getCpf());
        pacienteRequest.setTelefone(paciente.getTelefone());
        pacienteRequest.setDataNascimento(paciente.getDataNascimento());
        pacienteRequest.setEmail(paciente.getUsuario().getEmail());
        pacienteRequest.setSenha(paciente.getUsuario().getSenha());
        pacienteRequest.setIdPlano(paciente.getPlano().getId());

        return pacienteRequest;
    }

    public PacienteResponseDTO pacienteResponse(Paciente paciente)
    {
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getUsuario().getEmail(),
                paciente.getTelefone(),
                paciente.getPlano(),
                paciente.getUsuario().getId()
        );

        return pacienteResponseDTO;
    }

    @Transactional
    public PacienteResponseDTO inserirOdontoPaciente(PacienteRequestDTO pacienteRequestDTO) {

        entityManager.createNativeQuery("CALL inserir_paciente(:p_nome, :p_cpf, :p_data_nascimento, :p_telefone, :p_planos_id, :p_email, :p_senha, :p_tipo_usuario)")
                .setParameter("p_nome", pacienteRequestDTO.getNome())
                .setParameter("p_cpf", pacienteRequestDTO.getCpf())
                .setParameter("p_data_nascimento", pacienteRequestDTO.getDataNascimento())
                .setParameter("p_telefone", pacienteRequestDTO.getTelefone())
                .setParameter("p_planos_id", pacienteRequestDTO.getIdPlano())
                .setParameter("p_email", pacienteRequestDTO.getEmail())
                .setParameter("p_senha", pacienteRequestDTO.getSenha())
                .setParameter("p_tipo_usuario", Enum_tipo_usuario.PACIENTE.toString())
                .executeUpdate();

        System.out.println("Paciente inserido com sucesso!");

        Paciente pacienteInserido = (Paciente) entityManager.createQuery("SELECT p FROM Paciente p WHERE p.cpf = :cpf")
                .setParameter("cpf", pacienteRequestDTO.getCpf())
                .getSingleResult();

        return pacienteResponse(pacienteInserido);
    }

    @Transactional
    public PacienteResponseDTO updateOdontoPaciente(PacienteRequestDTO pacienteRequestDTO, Long idPaciente) {
        try {
            entityManager.createNativeQuery("CALL atualizar_paciente(:p_paciente_id, :p_nome, :p_cpf, :p_data_nascimento, :p_telefone, :p_planos_id, :p_email, :p_senha)")
                    .setParameter("p_paciente_id", idPaciente)
                    .setParameter("p_nome", pacienteRequestDTO.getNome())
                    .setParameter("p_cpf", pacienteRequestDTO.getCpf())
                    .setParameter("p_data_nascimento", pacienteRequestDTO.getDataNascimento())
                    .setParameter("p_telefone", pacienteRequestDTO.getTelefone())
                    .setParameter("p_planos_id", pacienteRequestDTO.getIdPlano())
                    .setParameter("p_email", pacienteRequestDTO.getEmail())
                    .setParameter("p_senha", pacienteRequestDTO.getSenha())
                    .executeUpdate();

            Paciente pacienteInserido = (Paciente) entityManager.createQuery("SELECT p FROM Paciente p WHERE p.cpf = :cpf")
                    .setParameter("cpf", pacienteRequestDTO.getCpf())
                    .getSingleResult();

            return pacienteResponse(pacienteInserido);

        } catch (PersistenceException e) {

            Throwable cause = e.getCause();

            if (cause instanceof SQLException) {
                throw new RuntimeException("Erro do banco: " + cause.getMessage());
            }

            throw new RuntimeException("Erro inesperado: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteOdontoPaciente(Long idPaciente) {
        try {
            entityManager.createNativeQuery("CALL deletar_paciente(:p_id)")
                    .setParameter("p_id", idPaciente)
                    .executeUpdate();

        } catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof SQLException) {
                throw new RuntimeException("Erro do banco: " + cause.getMessage());
            }
            throw new RuntimeException("Erro inesperado: " + e.getMessage());
        }
    }
}


