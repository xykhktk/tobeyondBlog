package com.tobeyond.blog.mq;

import com.tobeyond.blog.util.DateKit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        String context = message + " " + DateKit.dateFormat(DateKit.getNow());
        this.rabbitTemplate.convertAndSend("queue-test", context);
    }

}