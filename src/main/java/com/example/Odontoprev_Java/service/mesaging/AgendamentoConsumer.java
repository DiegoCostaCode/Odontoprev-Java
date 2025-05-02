package com.example.Odontoprev_Java.service.mesaging;

import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoResponseDTO;
import com.example.Odontoprev_Java.DTO.mistralDTO.MistralPromptResponseDTO;
import com.example.Odontoprev_Java.config.RabbitMqConfig;
import com.example.Odontoprev_Java.service.ollamaModel.MistralService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoConsumer {

    @Autowired
    private MistralService mistralService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void processarAgendamento(AgendamentoResponseDTO agendamento) {
        System.out.println("Agendamento recebido: " + agendamento);

        MistralPromptResponseDTO resultado = mistralService.verificarAgendamento(agendamento);

        if (resultado.fraude()) {
            System.out.println("Fraude detectada no agendamento ID: " + agendamento.id());
        } else {
            System.out.println("Agendamento ID " + agendamento.id() + " verificado como legítimo.");
        }

        System.out.println("Detalhes da IA: " + resultado.resposta());
    }
}