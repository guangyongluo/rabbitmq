package com.vilin.rabbitmq.consumer;

import com.rabbitmq.client.*;
import com.vilin.rabbitmq.util.ConnectionUtil;

import java.io.IOException;

public class Consumer2 {

    public static void main(String[] args) throws Exception{
        //获得connection
        Connection connection = ConnectionUtil.getConnection();
        //创建channel
        Channel channel = connection.createChannel();

        channel.queueDeclare("queue2", true, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,  "UTF-8"));
            }
        };

        channel.basicConsume("queue2", true, consumer);
    }

}
