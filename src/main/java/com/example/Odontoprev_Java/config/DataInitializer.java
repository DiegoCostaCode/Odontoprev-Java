package com.example.Odontoprev_Java.config;


import com.example.Odontoprev_Java.DTO.auditorDTO.AuditorRequestDTO;
import com.example.Odontoprev_Java.DTO.clinicaDTO.ClinicaRequestDTO;
import com.example.Odontoprev_Java.DTO.pacienteDTO.PacienteRequestDTO;
import com.example.Odontoprev_Java.DTO.planosDTO.PlanoRequestDTO;
import com.example.Odontoprev_Java.DTO.procedimentoDTO.ProcedimentoRequestDTO;
import com.example.Odontoprev_Java.model.Plano;
import com.example.Odontoprev_Java.model.procedimento.Enum_procedimento;
import com.example.Odontoprev_Java.model.procedimento.Procedimento;
import com.example.Odontoprev_Java.repository.*;
import com.example.Odontoprev_Java.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final PlanosRepository planoRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final ClinicaRepository clinicaRepository;
    private final PacienteRepository pacienteRepository;
    private final AuditorRepository auditorRepository;
    private final ProcedimentoService procedimentoService;
    private final ClinicaService clinicaService;
    private final PlanoService planoService;
    private final PacienteService pacienteService;
    private final AuditorService auditorService;

    public DataInitializer(PlanosRepository planoRepository, ProcedimentoRepository procedimentoRepository, ClinicaRepository clinicaRepository, PacienteRepository pacienteRepository, AuditorRepository auditorRepository, ProcedimentoService procedimentoService, ClinicaService clinicaService, PlanoService planoService, PacienteService pacienteService, AuditorService auditorService) {
        this.planoRepository = planoRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.clinicaRepository = clinicaRepository;
        this.pacienteRepository = pacienteRepository;
        this.auditorRepository = auditorRepository;
        this.procedimentoService = procedimentoService;
        this.clinicaService = clinicaService;
        this.planoService = planoService;
        this.pacienteService = pacienteService;
        this.auditorService = auditorService;
    }

    @Bean
    public CommandLineRunner loadData() {

        return args -> {
            logger.info("Iniciando carga de dados iniciais...");

            if (planoRepository.count() == 0) {
                logger.info("Inserindo plano padrão...");
                PlanoRequestDTO planoRequestDTO = new PlanoRequestDTO();
                planoRequestDTO.setNome("Plano Básico");
                planoRequestDTO.setDescricao("Plano básico de saúde bucal");
                planoRequestDTO.setPreco(99.99);

                Plano plano = planoService.savePlano(planoRequestDTO);
                planoService.ativarPlano(plano.getId());
                logger.info("Plano inserido com sucesso.");
            }

            if (procedimentoRepository.count() == 0) {
                logger.info("Inserindo procedimento padrão...");
                ProcedimentoRequestDTO proc = new ProcedimentoRequestDTO();
                proc.setTitulo(Enum_procedimento.LIMPEZA);
                proc.setDescricao("""
                        Procedimento de limpeza bucal realizado com profilaxia, remoção de placa bacteriana e polimento dentário. 
                        Sem intercorrências ou necessidade de procedimentos adicionais. Atendimento concluído conforme o previsto no agendamento.
                        """);
                proc.setValorCobertura(120.00);

                Procedimento procedimento = procedimentoService.save(proc);
                procedimentoService.ativarProc(procedimento.getId());

                logger.info("Procedimento inserido com sucesso.");
            }

            if (clinicaRepository.count() == 0) {
                logger.info("Inserindo clínica padrão...");
                ClinicaRequestDTO clinica = new ClinicaRequestDTO();
                clinica.setRazaosocial("Clínica Sorriso Ltda");
                clinica.setCnpj("51.153.584/0001-76");
                clinica.setEmail("clinica@sorriso.com");
                clinica.setSenha("clinica123@");
                clinica.setTelefone("(11) 99868-6061");
                clinicaService.saveClinica(clinica);
                logger.info("Clínica inserida com sucesso.");
            }

            if (pacienteRepository.count() == 0) {
                logger.info("Inserindo paciente padrão...");

                PacienteRequestDTO paciente = new PacienteRequestDTO();
                paciente.setNome("João da Silva");
                paciente.setCpf("085.477.241-33");
                paciente.setEmail("joao@gmail.com");
                paciente.setTelefone("(11) 98765-4321");
                paciente.setDataNascimento(LocalDate.of(2005, 2, 16));
                paciente.setIdPlano(planoService.findById(1L).getId());
                paciente.setSenha("paciente123@");
                pacienteService.savePaciente(paciente);
                logger.info("Paciente inserido com sucesso.");
            }

            if (auditorRepository.count() == 0) {
                logger.info("Inserindo auditor padrão...");
                AuditorRequestDTO auditor = new AuditorRequestDTO();
                auditor.setNome("Auditor Odontoprev");
                auditor.setEmail("auditor@odontoprev.com");
                auditor.setSenha("auditor123@");
                auditor.setTelefone("(21) 99181-9295");
                auditorService.saveAuditor(auditor);
                logger.info("Auditor inserido com sucesso.");
            }

            logger.info("Carga de dados iniciais finalizada.");
        };
    }
}
