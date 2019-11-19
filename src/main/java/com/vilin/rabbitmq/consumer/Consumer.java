package com.vilin.rabbitmq.consumer;

import com.rabbitmq.client.*;
import com.vilin.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.nio.charset.Charset;

public class Consumer {

    public static void main(String[] args) throws Exception{
        //获得connection
        Connection connection = ConnectionUtil.getConnection();
        //创建channel
        Channel channel = connection.createChannel();

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,  "UTF-8"));

                System.out.println("消息消费成功");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(ConnectionUtil.QUEUE_NAME, false, consumer);
    }

}
