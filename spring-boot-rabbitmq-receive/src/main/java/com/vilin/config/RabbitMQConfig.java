package com.vilin.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String RABBITMQ_HOST = "192.168.193.132";

    public static final int RABBITMQ_PORT = 5672;

    public static final String RABBITMQ_USERNAME= "admin";

    public static final String RABBITMQ_PASSWORD= "admin@123";

//    public static final String VIRTUAL_HOST = "testhost";

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(RABBITMQ_HOST);
        connectionFactory.setPort(RABBITMQ_PORT);
        connectionFactory.setUsername(RABBITMQ_USERNAME);
        connectionFactory.setPassword(RABBITMQ_PASSWORD);
//        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
//        connectionFactory.setVirtualHost(VIRTUAL_HOST);
        //是否开启消息确认机制
        //connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }


}
