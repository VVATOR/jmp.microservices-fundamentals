package com.epam.learn.microservices.fundamental.processors.resource.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.template.exchange}")
    private String processingExchange;

    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String processingQueueName;


    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        final var cachingConnectionFactory = new CachingConnectionFactory(host, port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public Queue processingQueue() {
        return new Queue(processingQueueName, false);
    }

    @Bean
    public FanoutExchange processingExchange() {
        return ExchangeBuilder.fanoutExchange(processingExchange).build();
    }

    @Bean
    public Binding processingBinding(Queue processingQueue, FanoutExchange processingExchange) {
        return BindingBuilder.bind(processingQueue).to(processingExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}
