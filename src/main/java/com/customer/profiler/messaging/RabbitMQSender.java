package com.customer.profiler.messaging;

import com.customer.profiler.service.models.CustomerProfile;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${profiles.rabbitmq.exchange}")
    private String exchange;

    @Value("${profiles.rabbitmq.routingkey}")
    private String routingkey;

    public void send(CustomerProfile customerProfile) {
        rabbitTemplate.convertAndSend(exchange, routingkey, customerProfile);
        System.out.println("Send msg = " + customerProfile.getProfileId());
    }
}