package com.mateus.creditanalysis.service;

import com.mateus.creditanalysis.domain.Proposals;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQNotificationService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notify(String exchange, Proposals proposals) {
        rabbitTemplate.convertAndSend(exchange, "", proposals);
    }
}
