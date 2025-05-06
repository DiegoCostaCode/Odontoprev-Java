package com.example.Odontoprev_Java.service.mesaging;

import com.example.Odontoprev_Java.DTO.agendamentoDTO.AgendamentoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoProducer {

    private static final Logger log = LoggerFactory.getLogger(AgendamentoProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;


    public AgendamentoProducer(RabbitTemplate rabbitTemplate,
                               @Value("${app.rabbitmq.exchange}") String exchange,
                               @Value("${app.rabbitmq.routingkey}")String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void enviarParaFIladeAnaliseAI(AgendamentoResponseDTO agendamento) {
        log.info("Enviando agendamento para fila de análise: {}", agendamento);
        rabbitTemplate.convertAndSend(exchange, routingKey, agendamento);
        log.info("Agendamento enviado para fila de análise!");
    }

}
