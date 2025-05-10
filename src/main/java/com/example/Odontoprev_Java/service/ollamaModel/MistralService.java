package com.example.Odontoprev_Java.service.ollamaModel;


import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoResponseDTO;
import com.example.Odontoprev_Java.DTO.mistralDTO.MistralPromptResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class MistralService {

    private ChatLanguageModel model;

    @PostConstruct
    public void MistralInit() {

        this.model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("mistral")
                .build();
    }


    public MistralPromptResponseDTO verificarAgendamento(AgendamentoResponseDTO agendamento) {
        String prompt = gerarPromptDeFraude(agendamento);

        if (model == null) {
            throw new IllegalStateException("O modelo não foi inicializado corretamente.");
        }

        String resposta = model.generate(prompt);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(resposta, MistralPromptResponseDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a resposta da IA", e);
        }
    }



    public String gerarPromptDeFraude(AgendamentoResponseDTO agendamento) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return String.format(
                """
                Você é um assistente virtual de um auditor de uma empresa de saúde. Sua tarefa é analisar os dados de um agendamento e determinar se ele é suspeito de fraude.
                
                As datas estão no formato "dd/MM/yyyy HH:mm". Valores monetários estão em reais (R$).
                
                Analise os seguintes pontos com atenção:
                
                1. **Duração da consulta**: Verifique se há inconsistência entre a Data do Agendamento e a data de Finalização (tempo muito curto ou longo pode indicar fraude).
                2. **Descrição do atendimento**: Compare com a descrição esperada do procedimento (ou com palavras-chave esperadas). Caso a descrição esteja fora do escopo do procedimento, indique possível fraude.
                3. **Procedimentos realizados**: Verifique se houve adição de procedimentos não previstos originalmente. Fique atento a por exemplo: Limpeza, não se pode ter remoção de dentes. 
                4. **Preço do atendimento**: Compare com o valor de cobertura da empresa para identificar discrepâncias.
                
                Aqui estão os detalhes do agendamento para sua análise:
                
                ID do Agendamento: %d  
                Data do Agendamento: %s  
                Finalizado em: %s  
                Status do Agendamento: %s  
                Paciente: %s  
                Clínica: %s  
                Procedimento: %s  
                Cobertura de nossa empresa para o Procedimento: R$ %.2f  
                Preço do Atendimento: R$ %.2f  
                Descrição do atendimento (visão clínica): %s  
                Descrição do procedimento (visão Odontoprev): %s
                
                Com base nesses dados, determine se este agendamento apresenta sinais de fraude ou irregularidades. Sua resposta deve estar no formato JSON abaixo:
                
                {
                "fraude": true/false,
                "descricao_coerente": true/false,
                "resposta": "Explique claramente o motivo da classificação. Destaque sinais observados como duração atípica, preço fora da cobertura, ou descrição incompatível com o procedimento. Mesmo que não haja fraude, justifique brevemente o porquê."
                }
                """,
                agendamento.id(),
                agendamento.dataAgendamento().format(formatter),
                agendamento.finalizadoEm().format(formatter),
                agendamento.status(),
                agendamento.paciente(),
                agendamento.clinica(),
                agendamento.procedimento(),
                agendamento.procedimentoCobertura(),
                agendamento.atendimentoValor(),
                agendamento.descricaoAtendimento(),
                agendamento.descricaoProcedimento()
        );


    }


}




