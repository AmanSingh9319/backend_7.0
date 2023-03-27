

package com.example.MovieService.Config;

import com.example.MovieService.rabbitmq.MovieDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageToRabbitMq(MovieDTO movieDTO)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),MessageConfiguration.MESSAGE_KEY,movieDTO);
    }

}