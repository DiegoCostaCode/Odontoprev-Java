package com.example.Odontoprev_Java.service.mesaging;

import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoProducer {

    private final RabbitTemplate rabbitTemplate;

    public AgendamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarParaFIladeAnaliseAI(AgendamentoResponseDTO agendamento) {
        rabbitTemplate.convertAndSend("agendamento.exchange", "agendamento.routingkey", agendamento);
    }

//    public void enviarParaFIladeAnaliseAI(String mensagem) {
//        rabbitTemplate.convertAndSend("agendamento.exchange", "agendamento.routingkey", mensagem);
//    }
}
