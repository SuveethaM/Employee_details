package com.example.demo.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
public class RabbitMqConfig{

    private final RabbitTemplate rabbitTemplate;

    // Constructor with RabbitTemplate parameter
    public RabbitMqConfig(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Bean
    public Queue queue() {
        return new Queue("myQueue", true);
    }
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("myDirectExchange");

    }
    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        // Bind the queue to the exchange with a specific routing key
        return BindingBuilder.bind(queue).to(directExchange).with("myRoutingKey");
    }

}