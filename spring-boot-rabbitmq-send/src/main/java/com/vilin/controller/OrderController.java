package com.vilin.controller;

import com.vilin.util.RabbitMQSendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    RabbitMQSendMessageUtil rabbitMQSendMessageUtil;

    @RequestMapping("/order.do")
    public Object order(String exchangeName, String routingKey, String message){
        rabbitMQSendMessageUtil.sendMessage(exchangeName, routingKey, message);
        return "下单成功！";
    }
}
