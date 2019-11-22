package com.vilin.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static String HOST = "192.168.193.132";

    public static int PORT = 5672;

    public static String USERNAME = "admin";

    public static String PASSWORD = "admin@123";

    public static String QUEUE_NAME= "test_queue";

    public  static Connection getConnection() throws Exception{
        //创建一个RabbitMQ连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置RabbitMQ服务器所在地址
        connectionFactory.setHost(HOST);
        //设置端口号，连接用户名等
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        return connectionFactory.newConnection();
    }
}
