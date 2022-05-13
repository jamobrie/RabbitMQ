package com.example.messagingrabbitmq;

import com.example.messagingrabbitmq.config.RabbitMQ;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final StudyListener studyListener;

    public Runner(StudyListener studyListener, RabbitTemplate rabbitTemplate) {
        this.studyListener = studyListener;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQ.topicExchangeName, "deciphex.study.baz", "Hello from RabbitMQ aksdjbaskhdbasjkhdvasjdhvaskdjv hsbkdfjlnkjfsdkhvgsufyiudonipkcvjdbhigrewueoniqdpml[SAXCMKLVJDBIHGREUNOIFWPMDSLACKSJ VDBIFHGUR9HEIF0VJDCOPASM!");
        studyListener.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
