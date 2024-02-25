package com.epam.learn.microservices.fundamental.services.resources.messaging.rabbitmq;

import com.epam.learn.microservices.fundamental.services.resources.services.ResourceProcessorSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqResourceProcessorSender implements ResourceProcessorSender {
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Autowired
    public RabbitMqResourceProcessorSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Integer resourceId) {
        try {
            log.info("Sending started.");
            rabbitTemplate.convertAndSend(exchange, routingKey, resourceId);
            log.info("Sending finished.");
        } catch (Exception e) {
            log.error("Error " + e.getMessage());
        }
    }
}
