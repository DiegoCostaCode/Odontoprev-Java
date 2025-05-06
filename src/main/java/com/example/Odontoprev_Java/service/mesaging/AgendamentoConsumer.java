package com.example.Odontoprev_Java.service.mesaging;

import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoResponseDTO;
import com.example.Odontoprev_Java.DTO.mistralDTO.MistralPromptResponseDTO;
import com.example.Odontoprev_Java.service.ollamaModel.MistralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoConsumer {

    private static final Logger log = LoggerFactory.getLogger(AgendamentoConsumer.class);

    @Autowired
    private MistralService mistralService;

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void processarAgendamento(AgendamentoResponseDTO agendamento) {
        log.info("Agendamento recebido: {}", agendamento);

        MistralPromptResponseDTO resultado = mistralService.verificarAgendamento(agendamento);

        if (resultado.fraude()) {
            log.warn("Fraude detectada no agendamento ID: {}", agendamento.id());
        } else {
            log.info("Agendamento ID {} verificado como legítimo.", agendamento.id());
        }

        log.info("Detalhes da IA: {}", resultado.resposta());
    }
}