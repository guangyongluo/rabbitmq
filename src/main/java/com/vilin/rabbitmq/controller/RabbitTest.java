package com.vilin.rabbitmq.controller;

import com.vilin.rabbitmq.sender.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
    @Autowired
    private HelloSender1 helloSender1;

    @Autowired
    private HelloSender2 helloSender2;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private DirectSender directSender;

    @Autowired
    private HeadersSender headersSender;

    @Autowired
    private DistributionSender distributionSender;

    @Autowired
    private TransactionSender2 transactionSender;

    /**
     * 单生产者-单消费者
     */
    @GetMapping("/hello")
    public void hello() {
        helloSender1.send("hello1");
    }

    /**
     * 单生产者-多消费者
     */
    @GetMapping("/oneToMany")
    public void oneToMany() {
        for (int i = 0; i < 4; i++) {
            helloSender1.send("第[" + (i + 1) + "]个 ---------> ");
        }
    }

    /**
     * 多生产者-多消费者
     */
    @GetMapping("/manyToMany")
    public void manyToMany() {
        for (int i = 0; i < 4; i++) {
            helloSender1.send("第[" + (i + 1) + "]个 ---------> ");
            helloSender2.send("第[" + (i + 1) + "]个 ---------> ");
        }

    }

    /**
     * topic exchange类型rabbitmq测试
     */
    @GetMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    /**
     * fanout exchange类型rabbitmq测试
     */
    @GetMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send();
    }

    /**
     * direct exchange类型rabbitmq测试
     */
    @GetMapping("/directTest")
    public void directTest() {
        directSender.send();
    }

    /**
     * headers exchange类型rabbitmq测试
     */
    @GetMapping("/headersTest")
    public void headersTest() {
        headersSender.send();
    }

    /**
     * 分发机制消息发送测试
     */
    @GetMapping("/distribu")
    public void distribu() {
        //distributionSender.send(3);
        for (int i = 0; i < 5; i++) {
            //发送任务复杂度都为1的消息
            distributionSender.send(1);
        }
    }

    /**
     * 事务消息发送测试
     */
    @GetMapping("/transition")
    public void transition() {
        transactionSender.send("Transition:  ");
    }
}
