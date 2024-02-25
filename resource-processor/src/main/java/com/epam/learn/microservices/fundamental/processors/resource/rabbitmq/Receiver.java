package com.epam.learn.microservices.fundamental.processors.resource.rabbitmq;

import com.epam.learn.microservices.fundamental.processors.resource.services.Processor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
public class Receiver {

    private Processor processor;

    @Autowired
    public Receiver(Processor processor) {
        this.processor = processor;
    }

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveResourceId(Integer resourceId) {
        log.info("Received resourceId: " + resourceId);
        processor.processResource(resourceId);
    }
}
