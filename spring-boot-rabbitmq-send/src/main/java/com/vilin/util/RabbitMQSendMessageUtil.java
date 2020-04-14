package com.vilin.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSendMessageUtil {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchangeName, String routingKey, String message){
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
