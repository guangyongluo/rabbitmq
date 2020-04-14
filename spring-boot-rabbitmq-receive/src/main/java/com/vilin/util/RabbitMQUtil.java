package com.vilin.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQUtil {
    @RabbitListener(queues = "queue1")
    public void get(String message) throws Exception{
        System.out.println(message);
    }
}
