package com.tobeyond.blog.task;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue-test")
public class MqListener {

    @RabbitHandler
    public void process(String message){
        System.out.println("get message " + message);
    }

}
