package com.vilin.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.vilin.rabbitmq.util.ConnectionUtil;


public class Producer {

    public static void main(String[] args) throws Exception{
        //获得connection
        Connection connection = ConnectionUtil.getConnection();
        //创建channel
        Channel channel = connection.createChannel();

        //删除交换器
        channel.exchangeDelete("exchangeTest");

        //声明交换器
        channel.exchangeDeclare("exchangeTest", BuiltinExchangeType.TOPIC);

        //绑定交换器和队列
        channel.queueBind("queue1", "exchangeTest", "debug.*.B");
        channel.queueBind("queue2", "exchangeTest", "error.#");
        channel.queueBind("queue3", "exchangeTest", "*.email.*");

//        channel.queueDeclare(ConnectionUtil.QUEUE_NAME, true, false, false, null);

        String[] s1 = new String[]{"error", "debug", "info"};
        String[] s2 = new String[]{"user", "order", "email"};
        String[] s3= new String[]{"A", "B", "C"};

        for(int i = 0; i < s1.length; i++){
            for(int j = 0; j < s2.length; j++){
                for(int k = 0; k < s3.length; k++){
                    System.out.println(s1[i] + "." + s2[j] + "." + s3[k]);
                    String message = s1[i] + "." + s2[j] + "." + s3[k];
                    channel.basicPublish("exchangeTest", message, null, message.getBytes());
                }
            }
        }


        channel.basicPublish("exchangeTest", "info.user", null, "hello".getBytes());
        channel.close();
        connection.close();
    }
}
