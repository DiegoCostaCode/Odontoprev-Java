package com.example.Odontoprev_Java.service;

import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoPacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoResponseDTO;
import com.example.Odontoprev_Java.model.agendamento.Agendamento;
import com.example.Odontoprev_Java.model.Clinica;
import com.example.Odontoprev_Java.model.Paciente;
import com.example.Odontoprev_Java.model.procedimento.Procedimento;
import com.example.Odontoprev_Java.repository.AgendamentoRepository;
import com.example.Odontoprev_Java.service.mesaging.AgendamentoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private ProcedimentoService procedimentoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private AgendamentoProducer producer;


    public Agendamento save(AgendamentoPacienteRequestDTO AgendamentoPaciente)
    {

        Clinica clinica = clinicaService.findById(AgendamentoPaciente.getClinicaId());
        Paciente paciente = pacienteService.findById(AgendamentoPaciente.getPacienteId());
        Procedimento procedimento = procedimentoService.findById(AgendamentoPaciente.getProcedimentoId());

        Agendamento agendamento = new Agendamento();
        agendamento.setDataAgendamento(AgendamentoPaciente.getDataAgendamento());
        agendamento.setClinica(clinica);
        agendamento.setPaciente(paciente);
        agendamento.setProcedimento(procedimento);
        agendamento.setStatus(AgendamentoPaciente.getStatus());

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento clinicaUpdateAgendamento(Long idAgendamento,AgendamentoClinicaRequestDTO AgendamentoClinica)
    {

        Agendamento agendamento = findById(idAgendamento);

        if (agendamento == null) {
            return null;
        }

        agendamento.setFinalizadoEm(AgendamentoClinica.getFinalizadoEm());
        agendamento.setPrecoAtendimento(AgendamentoClinica.getPrecoAtendimento());
        agendamento.setDescricaoAtendimento(AgendamentoClinica.getDescricaoAtendimento());
        agendamento.setStatus(AgendamentoClinica.getStatus());

        Agendamento agendamentoInserted = agendamentoRepository.save(agendamento);

        AgendamentoResponseDTO agendamentoResponse = agendamentoResponse(agendamentoInserted);

        producer.enviarParaFIladeAnaliseAI(agendamentoResponse);

        return agendamentoInserted;
    }

    public AgendamentoResponseDTO agendamentoResponse(Agendamento agendamento){
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDataAgendamento(),
                agendamento.getFinalizadoEm(),
                agendamento.getStatus().getDescricao(),
                agendamento.getPaciente().getNome(),
                agendamento.getClinica().getRazaosocial(),
                agendamento.getPrecoAtendimento(),
                agendamento.getDescricaoAtendimento(),
                agendamento.getProcedimento().getTitulo().getDescricao(),
                agendamento.getProcedimento().getDescricao(),
                agendamento.getProcedimento().getValorCobertura(),
                agendamento.getPrecoAtendimento()

        );
    }

    public Agendamento findById(Long id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        return agendamento.orElse(null);
    }

    public List<Agendamento> findAgendamentoByPacienteId(Long pacienteId) {
        return agendamentoRepository.findByPacienteIdOrderByDataAgendamentoDesc(pacienteId);
    }

    public List<Agendamento> findAgendamentoByClinicaId(Long clinicaId) {

        return agendamentoRepository.findByClinicaIdOrderByDataAgendamentoDesc(clinicaId);
    }

}
