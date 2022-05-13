package com.example.messagingrabbitmq;

import com.example.messagingrabbitmq.config.RabbitMQ;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingRabbitmqApplication {

    private static RabbitTemplate rabbitTemplate;

    public MessagingRabbitmqApplication(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MessagingRabbitmqApplication.class, args);
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitMQ.topicExchangeName, "deciphex.study.baz", "Hello from RabbitMQ!");
        }

    }

}
