package com.tobeyond.blog.mq;

import com.tobeyond.blog.controller.socket.RabbitMqConfigSocket;
import com.tobeyond.blog.util.DateKit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue-test")
public class MqListener {

    @Autowired
    RabbitMqConfigSocket rabbitMqConfigSocket;

    @RabbitHandler
    public void process(String message){
        System.out.println("MqListener get message " + message);
        rabbitMqConfigSocket.sendAll(message);
    }

}
